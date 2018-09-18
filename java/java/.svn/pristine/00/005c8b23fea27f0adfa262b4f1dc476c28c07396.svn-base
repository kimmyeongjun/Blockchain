package util;

public class SAByteUtil {
    public static Byte DEFAULT_BYTE = new Byte((byte) 0);

    public static byte[] toShortByte(short src) {
        byte[] result = new byte[2];
        result[1] = (byte) src;
        result[0] = (byte) ((src >> 8) & 0xff);
        return result;
    }
}