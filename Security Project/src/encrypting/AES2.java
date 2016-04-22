package encrypting;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES2 {
	
	
	static String IV = "AAAAAAAAAAAAAAAA";
	static String encryptionKey = "0123456789abcdef";

	public static byte[] encrypt(String plainText)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	public static String decrypt(byte[] cipherText)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}
	
	public static void main(String[] args) throws Exception {
		String str = "Mariam";
		byte[] enc = encrypt(str);
		System.out.println(enc.toString());
		String dec = decrypt(enc);
		System.out.println(dec);
	}

}
