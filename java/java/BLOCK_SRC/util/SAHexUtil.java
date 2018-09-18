package util;


public class SAHexUtil {
	// HexBinaryAdapter --> HEX ���� Ŭ����
	// DatatypeConverterImpl --> HEX ���� Ŭ����
	private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

	/**
	 * 16���� ���ڿ��� ����Ʈ �迭�� ��ȯ
	 * 
	 * @param hexStr
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public static byte[] hexStrToByteArr(String hexStr)  {
		if (hexStr == null) {
			return null;
		}

		int length = hexStr.length();
		length = length / 2;
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			int index = i * 2;
			bytes[i] = (byte) (Short.parseShort(hexStr.substring(index, index + 2), 16));
		}

		return bytes;
	}

	/**
	 * unsigned byte(����Ʈ) �迭�� 16���� ���ڿ��� ��ȯ
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteArrToHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		char[] hexChars = new char[bytes.length * 2];

		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i];
			hexChars[i * 2] = hexCode[(v & 0xF0) >> 4];
			hexChars[(i * 2) + 1] = hexCode[v & 0x0F];
		}

		String output = new String(hexChars);

		SAUtil.release(bytes);
		SAUtil.release(hexChars);

		return output;
	}

}
