package encrypting;

import java.io.File;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESede {

	public static byte[] encrypt(byte[] message) {
		try {
			// Create an array to hold the key
			byte[] encryptKey = "This is a test DESede key".getBytes();

			// Create a DESede key spec from the key
			DESedeKeySpec spec = new DESedeKeySpec(encryptKey);

			// Get the secret key factor for generating DESede keys
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");

			// Generate a DESede SecretKey object
			SecretKey theKey = keyFactory.generateSecret(spec);

			// Create a DESede Cipher
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

			// Create an initialization vector (necessary for CBC mode)

			IvParameterSpec IvParameters = new IvParameterSpec(new byte[] { 12,
					34, 56, 78, 90, 87, 65, 43 });

			// Initialize the cipher and put it into encrypt mode
			cipher.init(Cipher.ENCRYPT_MODE, theKey, IvParameters);

			

			// Encrypt the data
			byte[] encrypted = cipher.doFinal(message);
			return encrypted;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static String decrypt(byte[] encrypted) {
		try
	    {
	// Create an array to hold the key
	      byte[] encryptKey = "This is a test DESede key".getBytes();

	// Create a DESede key spec from the key
	      DESedeKeySpec spec = new DESedeKeySpec(encryptKey);

	// Get the secret key factor for generating DESede keys
	      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(
	        "DESede");

	// Generate a DESede SecretKey object
	      SecretKey theKey = keyFactory.generateSecret(spec);
	   

	// Create a DESede Cipher
	      Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
	      
	      

	// Create the initialization vector required for CBC mode
	      IvParameterSpec ivParameters = new IvParameterSpec(
	        new byte[] { 12, 34, 56, 78, 90, 87, 65, 43 } );

	// Initialize the cipher and put it in decrypt mode
	      cipher.init(Cipher.DECRYPT_MODE, theKey, ivParameters);

	     
	      byte[] plaintext = cipher.doFinal(encrypted);

	      String plaintextStr = new String(plaintext);

	      return plaintextStr;
	    }
	    catch (Exception exc)
	    {
	      exc.printStackTrace();
	    }
		return null;
	}

	public static void main(String[] args) {

		String message = "My name is mariam";
		byte[] enc = encrypt(message.getBytes());
		System.out.println("enc : " + enc.toString());
		String dec = decrypt(enc);
		System.out.println("dec : " + dec);
		byte[] enc2 = encrypt(message.getBytes());
		System.out.println("enc2 : " + enc2.toString());
		String dec2 = decrypt(enc2);
		System.out.println("dec : " + dec2);

	}

}
