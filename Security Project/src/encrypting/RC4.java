package encrypting;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RC4 {
	
	public static byte[] encrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		
		String password = "YOUR_SECRET_KEY";
				Cipher rc4 = Cipher.getInstance("RC4");
			
				rc4.init(Cipher.ENCRYPT_MODE,
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
	
	
	
	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//		Path path = Paths.get("/Users/apple/Desktop/test.rtf");
		
		
		// 1
		String xml1 = "Login";
		
		byte[] classBytes = xml1.getBytes();
		
		System.out.println("\n\n" + xml1);
//		
//	// 2
//		
		byte[] enc = encrypt("[B@3ac42916".getBytes());
		
		String xml2 = new String(enc.toString());
		System.out.println("\n\n" + xml2);
//		
		

//		String xmlk = "Login";
//		
//		byte[] classBytesk = xmlk.getBytes();
//		
//		System.out.println("\n\n" + xmlk);
//		
//		// 2
//		
//		byte[] enck = encrypt(classBytesk);
//		
//		String xml2k = new String(enck.toString());
//		System.out.println("\n\n" + xml2k);
//		
//		
//		///
//		
//		// 1
//		
//		
		// 3
		byte[] deck = decrypt(enc);
		
		String xml3k = new String(deck.toString());
		System.out.println("\n\n" + xml3k);
		
//		byte[] deck2 = decrypt(enck);
//		
//		String xml3k2 = new String(deck2);
//		System.out.println("\n\n" + xml3k2);
//		
//		// 3
//				byte[] dec = decrypt(enc);
//				
//				String xml3 = new String(dec);
//				System.out.println("\n\n" + xml3);
		
		
		
		
	}
	

}
