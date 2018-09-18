package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SAHashUtil {
	public static String ALG_HASH_1 = "SHA-1";
	public static String ALG_HASH_256 = "SHA-256";
	public static String ALG_HASH_512 = "SHA-512";

	public static byte[] sha256(String base) throws NoSuchAlgorithmException {
		return sha(base, ALG_HASH_256);
	}

	public static byte[] sha256(byte[] base) throws NoSuchAlgorithmException {
		byte[] result = null;

		result = sha(base, ALG_HASH_256);

		return result;
	}

	public static byte[] sha512(String base) throws NoSuchAlgorithmException {
		return sha(base, ALG_HASH_512);
	}

	public static byte[] sha512(byte[] base) throws NoSuchAlgorithmException {
		byte[] result = null;

		result = sha(base, ALG_HASH_512);

		return result;
	}

	public static byte[] sha(String base, String alg) throws NoSuchAlgorithmException {

		MessageDigest digest = null;
		digest = MessageDigest.getInstance(alg);

		byte[] hash = null;
		hash = digest.digest(base.getBytes());

		return hash;
	}

	public static byte[] sha(byte[] base, String alg) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(alg);
		return digest.digest(base);
	}

}