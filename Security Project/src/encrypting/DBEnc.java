package encrypting;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class DBEnc {
	
	
//	public static void main(String[] args) {
//		trial();
//	}

	
	public static SecretKey init() {
		
		SecretKey key = null;
		
		
		
		FileOutputStream fos = null;
	    FileInputStream fis = null;
	    
	    BufferedReader br = null;
	    
	    String encrypted = null;
	    String size = null;
	    
	    try{
	    	
	    	br = new BufferedReader(new FileReader("encrypted"));
	    	
	    	
	    	encrypted= br.readLine();
	    	String ln = br.readLine();
	    	int indexOfEqual = ln.indexOf('=');
	    	size = ln.substring(indexOfEqual + 1, ln.length());
	    	
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	    	if (br != null) {
	    		try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
		
	    if (encrypted.equals("true")) {
	    	try {
				fis = new FileInputStream("dbkey");
				byte[] b = new byte[Integer.parseInt(size)];
				fis.read(b);
				key = new SecretKeySpec(b, "DES");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	    } else {
	    	try {
				key = KeyGenerator.getInstance("DES").generateKey();
				byte[] b = key.getEncoded();
				PrintWriter pw = new PrintWriter("encrypted");
				pw.println("true");
				pw.println("size=" + String.valueOf(b.length));
				if (pw != null) {
					pw.close();
				}
				fos=new FileOutputStream("dbkey");
				fos.write(b);
			    fos.flush();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	    }
		
		return key;
	}
	
		
	    

	
	public static String encrptStr(String str) {
		Cipher ecipher;

		SecretKey key;
		
		
		try {
			key = init();
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

		SecretKey key;
		
		
		try {

			// generate secret key using DES algorithm

			key = init();
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
