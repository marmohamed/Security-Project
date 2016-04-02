package encrypting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class RC4 {
	
	public static byte[] encrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		
		String password = "YOUR_SECRET_KEY";
				Cipher rc4 = Cipher.getInstance("RC4");
				rc4.init(Cipher.DECRYPT_MODE,
				        new SecretKeySpec(password.getBytes(), "RC4")
				    );
				
			
		byte[] encrypted = rc4.doFinal(message);
		
		return encrypted;
	}
	
	
	public static byte[] decrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		
		String password = "YOUR_SECRET_KEY";
				Cipher rc4 = Cipher.getInstance("RC4");
				rc4.init(Cipher.DECRYPT_MODE,
				        new SecretKeySpec(password.getBytes(), "RC4")
				    );
				
			
		byte[] decrypted = rc4.doFinal(message);
		
		return decrypted;
	}
	
	
	
//	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//		Path path = Paths.get("/Users/apple/Desktop/test.rtf");
//		
//		byte[] classBytes = Files.readAllBytes(path);
//		
//		String xml1 = new String(classBytes);
//		System.out.println("\n\n" + xml1);
//		
//		byte[] enc = encrypt(classBytes);
//		
//		String xml2 = new String(enc);
//		System.out.println("\n\n" + xml2);
//		
//		byte[] dec = decrypt(enc);
//		
//		String xml3 = new String(dec);
//		System.out.println("\n\n" + xml3);
//		
//	}
	

}
