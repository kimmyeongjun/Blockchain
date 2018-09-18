package util;

public class SAUtil {
	public static int parseInt(String strNum) {
		int result = 0;

		if (strNum == null || strNum.length() < 1)
			return result;

		try {
			result = Integer.parseInt(strNum);
		} catch (Exception e) {
			return result;
		}

		return result;
	}

	public static long parseLong(String strNum) {
		long result = 0;

		if (strNum == null || strNum.length() < 1)
			return result;

		try {
			result = Long.parseLong(strNum);
		} catch (Exception e) {
			return result;
		}

		return result;
	}

	public static void release(byte[] data) {
		if (data == null)
			return;

		for (int i = 0; i < data.length; i++) {
			data[i] = 0x00;
		}
	}

	public static void release(char[] data) {
		if (data == null)
			return;

		for (int i = 0; i < data.length; i++) {
			data[i] = 0x00;
		}
	}

}
