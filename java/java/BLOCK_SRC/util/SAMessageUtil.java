package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class SAMessageUtil {

	/**
	 * 객체 내 변수값을 출력용으로 반환 출력 내용
	 * 
	 * ********************* 클래스명 ********************* [변수명] 변수내용
	 * 
	 * @param object
	 * @return 객체 출력용 String
	 */
	public static String getFieldInfo(Object object) {
		StringBuffer result = new StringBuffer();

		Class mClass = object.getClass();
		Field[] fields = mClass.getDeclaredFields();

		result.append("\n*********************\n");
		result.append(mClass.getName());
		result.append("\n*********************\n");
		for (Field field : fields) {
			result.append("[" + field.getName() + "]");
			result.append("\n");
			try {
				result.append(field.get(object));
			} catch (Exception e) {
			}
			result.append("\n");
		}
		result.append("*********************\n");

		return result.toString();
	}

	/**
	 * JSON String을 객체로 변환
	 * 
	 * @param jsonString
	 * @param object
	 */
	public static void parseJSONData(String jsonString, Object object) {
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
				e.printStackTrace();
		}

		ArrayList<String> keyArr = new ArrayList<String>();
		Iterator<?> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			keyArr.add(key);
		}

		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			for (String fieldName : keyArr) {
				if (fieldName.equals(field.getName())) {
					// field.set(fieldName, jsonObject.get(fieldName));
					try {
						cls.getField(fieldName).set(object, jsonObject.get(fieldName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 객체를 JSON string으로 변환
	 * 
	 * @param object
	 * @return
	 */
    public static String toJSON(Object object) throws JSONException {
    	JSONObject jsonObject = null;
    	try{
    		jsonObject = new JSONObject();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                jsonObject.put(field.getName(), field.get(object));
            } catch (Exception e) {
                throw new JSONException("error toJson()");
            }
        }

        return jsonObject.toString();
    }

	private static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		}

		return false;
	}

}