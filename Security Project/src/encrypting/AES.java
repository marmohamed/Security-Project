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
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	public static void main(String[] args) throws Exception {
		byte[] data = "abcdefghijkl".getBytes();
		byte[] tmp = null;
	
		tmp = encrypt(data);
		byte[] printString = decrypt(tmp);
		System.out.println(Arrays.equals(data, printString));

	}

	public static Object[] init() {

		Object[] result = new Object[2];

		SecretKey aesKey = null;

		IvParameterSpec iv = null;

		FileOutputStream fos = null;
		FileInputStream fis = null;

		BufferedReader br = null;

		String encrypted = null;
		String size = null;

		String ivSize = null;

		try {

			br = new BufferedReader(new FileReader("encrypted_aes_cbc"));

			encrypted = br.readLine();
			String ln = br.readLine();
			int indexOfEqual = ln.indexOf('=');
			size = ln.substring(indexOfEqual + 1, ln.length());

			ln = br.readLine();
			indexOfEqual = ln.indexOf('=');
			ivSize = ln.substring(indexOfEqual + 1, ln.length());

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
				fis = new FileInputStream("aes_cbc_key");
				byte[] b = new byte[Integer.parseInt(size)];
				fis.read(b);
				aesKey = new SecretKeySpec(b, "AES");

				fis = new FileInputStream("aes_cbc_iv");
				byte[] v = new byte[Integer.parseInt(ivSize)];
				fis.read(v);
				iv = new IvParameterSpec(v);
				
				

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
				aesKey = generateKey();
				iv = new IvParameterSpec("0102030405060708".getBytes());
				
				byte[] b = aesKey.getEncoded();
				
				byte[] v = iv.getIV();
				
				
				PrintWriter pw = new PrintWriter("encrypted_aes_cbc");
				pw.println("true");
				pw.println("sizekey=" + String.valueOf(b.length));
				pw.println("sizeiv=" + String.valueOf(v.length));
				if (pw != null) {
					pw.close();
				}
				fos = new FileOutputStream("aes_cbc_key");
				fos.write(b);
				fos.flush();
				fos = new FileOutputStream("aes_cbc_iv");
				fos.write(v);
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

		result[0] = aesKey;
		result[1] = iv;

		return result;

	}

	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static byte[] encrypt(byte[] data)
			throws Exception {
		Object[] obj = init();
		IvParameterSpec iv = (IvParameterSpec) obj[1];
		SecretKey encryptionKey = (SecretKey) obj[0];
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, iv);
		byte[] encryptData = cipher.doFinal(data);

		return encryptData;
	}

	public static byte[] decrypt(byte[] tmp)
			throws Exception {
		Object[] obj = init();
		IvParameterSpec iv = (IvParameterSpec) obj[1];
		SecretKey key = (SecretKey) obj[0];
		
		SecretKeySpec spec = new SecretKeySpec(key.getEncoded(),
				"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, spec, iv);

		System.out.println(tmp.length);
		return cipher.doFinal(tmp);

	}
}