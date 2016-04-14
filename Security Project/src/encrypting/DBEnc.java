package encrypting;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class DBEnc { 
	
	
	public static void main(String[] args) {
		String name = "Mariam";
		String enc1 = encrptStr(name);
		System.out.println(enc1);
		String dec1 = decreptStr(enc1);
		System.out.println(dec1);
		String enc2 = encrptStr(name);
		System.out.println(enc2);
		String dec2 = decreptStr(enc2);
		System.out.println(dec2);
		
		
		String namek = "Mariam";
		String enc1k = encrptStr(namek);
		System.out.println(enc1k);
		String dec1k = decreptStr(enc1k);
		System.out.println(dec1k);
		String enc2k = encrptStr(namek);
		System.out.println(enc2k);
		String dec2k = decreptStr(enc2k);
		System.out.println(dec2k);
		
	}

	
	
		
	    

	
	public static String encrptStr(String str) {
		Cipher ecipher;

		SecretKey key = null;
		
		byte[] encryptKey = "This is a test DES enc key".getBytes();

		// Create a DESede key spec from the key
		DESKeySpec spec = null;
		try {
			spec = new DESKeySpec(encryptKey);
			// Get the secret key factor for generating DESede keys
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DES");

			// Generate a DESede SecretKey object
			key = keyFactory.generateSecret(spec);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
//		String key_password = "key_in_string_des_";
//		byte[] b = key_password.getBytes();
		try {
//			key = init();
			
//			key = new SecretKeySpec(b, "DES");
		
			ecipher = Cipher.getInstance("DES");

			ecipher.init(Cipher.ENCRYPT_MODE, key);

			String encrypted = encrypt(str, ecipher);
//			System.out.println("String : " + str + " Enc : " + encrypted);
			return encrypted;

		}

		catch (InvalidKeyException e) {

			System.out.println("Invalid Key:" + e.getMessage());

			

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decreptStr(String str) {
		Cipher dcipher;

		SecretKey key = null;
		
		byte[] encryptKey = "This is a test DES enc key".getBytes();

		// Create a DESede key spec from the key
		DESKeySpec spec = null;
		try {
			spec = new DESKeySpec(encryptKey);
			// Get the secret key factor for generating DESede keys
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DES");

			// Generate a DESede SecretKey object
			key = keyFactory.generateSecret(spec);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {

			// generate secret key using DES algorithm

			//key = init();
			
//			key = new SecretKeySpec(b, "DES");
			
			dcipher = Cipher.getInstance("DES");

			dcipher.init(Cipher.DECRYPT_MODE, key);
			
			String decrypted = decrypt(str, dcipher);
//			System.out.println("Enc : " + str + " Dec : " + decrypted);
			return decrypted;

		}

		catch (InvalidKeyException e) {

			System.out.println("Invalid Key:" + e.getMessage());

			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String encrypt(String str, Cipher ecipher) {

		try {

			// encode the string into a sequence of bytes using the named
			// charset

			// storing the result into a new byte array.

			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64

			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String str, Cipher dcipher) {

		try {

			// decode with base64 to get bytes

			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset

			return new String(utf8, "UTF8");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

}
