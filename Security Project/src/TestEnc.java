import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import encrypting.AES;
import encrypting.AES2;
import encrypting.DBEnc;
import encrypting.DESede;
import encrypting.RC4;

public class TestEnc {

	public static HashMap<String, String> hm_enc = new HashMap<String, String>();
	public static HashMap<String, String> hm_dec = new HashMap<String, String>();

	public static void init() {
		hm_enc = new HashMap<String, String>();
		hm_enc.put("\\", "_backslash_");
		hm_enc.put("/", "_slash_");
		hm_enc.put("=", "_equal_");
		hm_enc.put("+", "_plus_");
		hm_enc.put("-", "_minus_");
		hm_enc.put(";", "_semicolon_");
		hm_enc.put("@", "_at_");
		hm_enc.put("[", "_open_brac_");
		hm_enc.put("]", "_closed_brac_");
		hm_enc.put("(", "_open_bran_");
		hm_enc.put(")", "_closed_bran_");
		hm_enc.put("{", "_open_curly_");
		hm_enc.put("}", "_closed_curly_");

		hm_dec = new HashMap<String, String>();
		hm_dec.put("_backslash_", "\\");
		hm_dec.put("_slash_", "/");
		hm_dec.put("_equal_", "=");
		hm_dec.put("_plus_", "+");
		hm_dec.put("_minus_", "-");
		hm_dec.put("_semicolon_", ";");
		hm_dec.put("_at_", "@");
		hm_dec.put("_open_brac_", "[");
		hm_dec.put("_closed_brac_", "]");
		hm_dec.put("_open_bran_", "(");
		hm_dec.put("_closed_bran_", ")");
		hm_dec.put("_open_curly_", "{");
		hm_dec.put("_closed_curly_", "}");
	}

	public static String encrypt(String str, String encryptionAlgorithm) {
		String result = "";
		byte[] temp;

		switch (encryptionAlgorithm) {
		case "rc4":

			try {
				temp = RC4.encrypt(str.getBytes());
				result = new String(temp.toString());

				FileWriter fw = new FileWriter("rc4_str_hash.txt", true); // the
																			// true
																			// will
																			// append
																			// the
																			// new
																			// data
				// System.out.println();
				fw.write(result + "," + str + "\n");// appends the string to the
													// file
				fw.close();

			} catch (InvalidKeyException | NoSuchAlgorithmException
					| NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "desede":
			temp = DESede.encrypt(str.getBytes());
			result = new String(temp.toString());

			FileWriter fw;
			try {
				fw = new FileWriter("desede_str_hash.txt", true);
				fw.write(result + "," + str + "\n");// appends the string to the
													// file
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // the true will append the new data
			// System.out.println();

			break;

		case "aes":
			try {
				temp = AES.encrypt(str.getBytes());
				result = new String(temp.toString());

				FileWriter fw2;

				fw2 = new FileWriter("aes_str_hash.txt", true);
				fw2.write(result + "," + str + "\n");// appends the string to
														// the file
				fw2.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "aes_ecb":
			try {
				temp = AES2.encrypt(str);
				result = new String(temp.toString());

				FileWriter fw2;

				fw2 = new FileWriter("aes_ecb_str_hash.txt", true);
				fw2.write(result + "," + str + "\n");// appends the string to
														// the file
				fw2.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

			
			
		default:
			result = DBEnc.encrptStr(str);
			break;
		}

		return result;
	}

	public static String decrypt(String str, String encryptionAlgorithm) {
		String result = "";
		byte[] temp;

		switch (encryptionAlgorithm) {
		case "rc4":

			try {

				String bytes = "";
				String line;
				BufferedReader br = new BufferedReader(new FileReader(
						"rc4_str_hash.txt"));
				while ((line = br.readLine()) != null) {
					if (line.startsWith(str + ",")) {
						int indexOfComma = line.indexOf(",");
						bytes = line.substring(indexOfComma + 1, line.length());
						break;
					}
				}
				br.close();

				result = bytes;

				// temp = RC4.decrypt(bytes.getBytes());
				// result = temp.toString();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "desede":
			try {

				String bytes = "";
				String line;
				BufferedReader br = new BufferedReader(new FileReader(
						"desede_str_hash.txt"));
				while ((line = br.readLine()) != null) {
					if (line.startsWith(str + ",")) {
						int indexOfComma = line.indexOf(",");
						bytes = line.substring(indexOfComma + 1, line.length());
						break;
					}
				}
				br.close();

				result = bytes;

				// temp = RC4.decrypt(bytes.getBytes());
				// result = temp.toString();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "aes":
			try {

				String bytes = "";
				String line;
				BufferedReader br = new BufferedReader(new FileReader(
						"aes_str_hash.txt"));
				while ((line = br.readLine()) != null) {
					if (line.startsWith(str + ",")) {
						int indexOfComma = line.indexOf(",");
						bytes = line.substring(indexOfComma + 1, line.length());
						break;
					}
				}
				br.close();

				result = bytes;

				// temp = RC4.decrypt(bytes.getBytes());
				// result = temp.toString();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "aes_ecb":
			try {

				String bytes = "";
				String line;
				BufferedReader br = new BufferedReader(new FileReader(
						"aes_ecb_str_hash.txt"));
				while ((line = br.readLine()) != null) {
					if (line.startsWith(str + ",")) {
						int indexOfComma = line.indexOf(",");
						bytes = line.substring(indexOfComma + 1, line.length());
						break;
					}
				}
				br.close();

				result = bytes;

				// temp = RC4.decrypt(bytes.getBytes());
				// result = temp.toString();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		

		default:
			result = DBEnc.decreptStr(str);
			break;
		}

		return result;
	}

	public static String replaceEnc(String str) {

		for (Map.Entry<String, String> entry : hm_enc.entrySet()) {
			if (str.contains(entry.getKey())) {
				str = str.replace(entry.getKey(), entry.getValue());
			}
		}

		return str;
	}

	public static String replaceDec(String str) {

		for (Map.Entry<String, String> entry : hm_dec.entrySet()) {
			if (str.contains(entry.getKey())) {
				str = str.replace(entry.getKey(), entry.getValue());
			}
		}

		return str;
	}

	public static void encryptJavaFile(String fileName,
			ArrayList<String> classNames, ArrayList<String> methodNames,
			ArrayList<String> varNames, String encryptionAlgorithm) {

		init();

		HashMap<String, String> encryptedBefore = new HashMap<String, String>();

		BufferedReader br;
		ArrayList<String> linesToWrite = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				String newLine = line;

				for (String className : classNames) {
					if (newLine.contains(className)) {
						// System.out.println("className : " + className + "m");
						String newStr = null;
						if (encryptedBefore.containsKey(className)) {
							newStr = encryptedBefore.get(className);
						} else {
							newStr = encrypt(className, encryptionAlgorithm);
							encryptedBefore.put(className, newStr);
						}
						newStr = "Cl_" + replaceEnc(newStr);
						// System.out.println("after : " + newStr + "m");
						newLine = newLine.replace(className, newStr);
					}
				}

				for (String methodName : methodNames) {
					if (newLine.contains(methodName)) {
						String newStr = null;
						if (encryptedBefore.containsKey(methodName)) {
							newStr = encryptedBefore.get(methodName);
						} else {
							newStr = encrypt(methodName, encryptionAlgorithm);
							encryptedBefore.put(methodName, newStr);
						}

						newStr = "meth_" + replaceEnc(newStr);
						newLine = newLine.replace(methodName, newStr);
					}
				}

				for (String varName : varNames) {
					if (newLine.contains(varName)) {
						String newStr = null;
						if (encryptedBefore.containsKey(varName)) {
							newStr = encryptedBefore.get(varName);
						} else {
							newStr = encrypt(varName, encryptionAlgorithm);
							encryptedBefore.put(varName, newStr);
						}
						newStr = "var_" + replaceEnc(newStr);
						newLine = newLine.replace(varName, newStr);
					}
				}
				linesToWrite.add(newLine);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter pw = null;

		int indexOfLastSlash = fileName.lastIndexOf("/");
		int indexOfDot = fileName.indexOf(".");

		String dir = "";
		if (indexOfLastSlash != -1) {
			dir = fileName.substring(0, indexOfLastSlash + 1);
		}

		String fileName2 = fileName.substring(indexOfLastSlash + 1, indexOfDot);

		// System.out.println("f : " + fileName2 + "m");

		String newStr = null;
		if (encryptedBefore.containsKey(fileName2)) {
			newStr = encryptedBefore.get(fileName2);
		} else {
			newStr = encrypt(fileName2, encryptionAlgorithm);
			encryptedBefore.put(fileName2, newStr);
		}

		newStr = "Cl_" + replaceEnc(newStr);

		// System.out.println("after2 : " + newStr + "m");

		String newFileName = dir + newStr
				+ fileName.substring(indexOfDot, fileName.length());

		try {

			File f = new File(newFileName);

			pw = new PrintWriter(f);
			for (String line : linesToWrite) {
				pw.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

	public static void decryptJavaFile(String fileName,
			ArrayList<String> classNames, ArrayList<String> methodNames,
			ArrayList<String> varNames, String encryptionAlgorithm) {

		init();

		// HashMap<String, String> encryptedBefore = new HashMap<String,
		// String>();

		BufferedReader br;
		ArrayList<String> linesToWrite = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				String newLine = line;

				for (String className : classNames) {
					if (newLine.contains(className)) {
						// System.out.println("className : " + className + "m");
						String newStr = null;
						String newClassName = className.substring(3,
								className.length());
						newStr = replaceDec(newClassName);
						newStr = decrypt(newStr, encryptionAlgorithm);

						// System.out.println("after : " + newStr + "m");
						// System.out.println(newStr);
						newLine = newLine.replace(className, newStr);
					}
				}

				for (String methodName : methodNames) {
					if (newLine.contains(methodName)) {
						String newStr = null;
						String newMethodName = methodName.substring(5,
								methodName.length());
						newStr = replaceDec(newMethodName);
						newStr = decrypt(newStr, encryptionAlgorithm);

						newLine = newLine.replace(methodName, newStr);
					}
				}

				for (String varName : varNames) {
					if (newLine.contains(varName)) {
						String newStr = null;
						String newVarName = varName.substring(4,
								varName.length());
						newStr = replaceDec(newVarName);
						newStr = decrypt(newStr, encryptionAlgorithm);

						newLine = newLine.replace(varName, newStr);
					}
				}
				linesToWrite.add(newLine);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter pw = null;

		int indexOfLastSlash = fileName.lastIndexOf("/");
		int indexOfDot = fileName.indexOf(".");

		String dir = "";
		if (indexOfLastSlash != -1) {
			dir = fileName.substring(0, indexOfLastSlash + 1);
		}

		String fileName2 = fileName.substring(indexOfLastSlash + 1, indexOfDot);

		// System.out.println("f : " + fileName2 + "m");

		String newStr = null;

		String tempname = fileName2.substring(3, fileName2.length());
		newStr = replaceDec(tempname);
		newStr = decrypt(newStr, encryptionAlgorithm);

		// System.out.println("after2 : " + newStr + "m");

		String newFileName = dir + newStr
				+ fileName.substring(indexOfDot, fileName.length());

		try {

			File f = new File(newFileName);

			pw = new PrintWriter(f);
			for (String line : linesToWrite) {
				pw.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// Login
//		 String fileName =
//		 "/Users/apple/Documents/workspace/Security Project/src/functions/Login.java";
//		
//		 ArrayList<String> classNames = new ArrayList<String>();
//		 classNames.add("Login");
//		
//		 ArrayList<String> methodNames = new ArrayList<String>();
//		 methodNames.add("login");
//		
//		 ArrayList<String> varNames = new ArrayList<String>();
//		
//		
//		 encryptJavaFile(fileName, classNames, methodNames, varNames, "rc4");
//		
//		 // Register
//		  String fileName2 =
//		 
//		 "/Users/apple/Documents/workspace/Security Project/src/functions/Register.java";
//		 
//		  ArrayList<String> classNames2 = new ArrayList<String>();
//		  classNames2.add("Register");
//		 
//		  ArrayList<String> methodNames2 = new ArrayList<String>();
//		  methodNames2.add("register");
//		 
//		  ArrayList<String> varNames2 = new ArrayList<String>();
//		 
//		 
//		  encryptJavaFile(fileName2, classNames2, methodNames2, varNames2,
//		 "aes");
//		 //
//		 // Deactivate
//		 String fileName3 =
//		 "/Users/apple/Documents/workspace/Security Project/src/functions/Deactivate.java";
//		
//		 ArrayList<String> classNames3 = new ArrayList<String>();
//		 classNames3.add("Deactivate");
//		
//		 ArrayList<String> methodNames3 = new ArrayList<String>();
//		 methodNames3.add("deactivate");
//		
//		 ArrayList<String> varNames3 = new ArrayList<String>();
//		
//		
//		 encryptJavaFile(fileName3, classNames3, methodNames3, varNames3,
//		 "desede");
//		 
//		 // Update
//		 String fileName4 =
//		 "/Users/apple/Documents/workspace/Security Project/src/functions/UpdateInfo.java";
//		
//		 ArrayList<String> classNames4 = new ArrayList<String>();
//		 classNames4.add("UpdateInfo");
//		
//		 ArrayList<String> methodNames4 = new ArrayList<String>();
//		 methodNames4.add("updateEmail");
//		 methodNames4.add("updatePassword");
//		 methodNames4.add("updateFirstName");
//		 methodNames4.add("updateLastName");
//		 methodNames4.add("updateAge");
//		
//		 ArrayList<String> varNames4 = new ArrayList<String>();
//		
//		
//		 encryptJavaFile(fileName4, classNames4, methodNames4, varNames4,
//		 "aes_ecb");

		
		// Decryption
		
		
		String fileName2 = "/Users/apple/Documents/workspace/Security Project/src/functions/Cl__open_brac_B_at_3ac42916.java";

		ArrayList<String> classNames2 = new ArrayList<String>();
		classNames2.add("Cl__open_brac_B_at_3ac42916");

		ArrayList<String> methodNames2 = new ArrayList<String>();
		methodNames2.add("meth__open_brac_B_at_47d384ee");

		ArrayList<String> varNames2 = new ArrayList<String>();

		decryptJavaFile(fileName2, classNames2, methodNames2, varNames2, "rc4");

		
		String fileName4 = "/Users/apple/Documents/workspace/Security Project/src/functions/Cl__open_brac_B_at_445b84c0.java";

		ArrayList<String> classNames4 = new ArrayList<String>();
		classNames4.add("Cl__open_brac_B_at_445b84c0");

		ArrayList<String> methodNames4 = new ArrayList<String>();
		methodNames4.add("meth__open_brac_B_at_61a52fbd");

		ArrayList<String> varNames4 = new ArrayList<String>();

		decryptJavaFile(fileName4, classNames4, methodNames4, varNames4,
				"aes");
		
		
		// Deactivate
		
		String fileName3 = "/Users/apple/Documents/workspace/Security Project/src/functions/Cl__open_brac_B_at_2b80d80f.java";

		ArrayList<String> classNames3 = new ArrayList<String>();
		classNames3.add("Cl__open_brac_B_at_2b80d80f");

		ArrayList<String> methodNames3 = new ArrayList<String>();
		methodNames3.add("meth__open_brac_B_at_3ab39c39");

		ArrayList<String> varNames3 = new ArrayList<String>();

		decryptJavaFile(fileName3, classNames3, methodNames3, varNames3,
				"desede");
		
		// UpdateInfo
		
		String fileName1 = "/Users/apple/Documents/workspace/Security Project/src/functions/Cl__open_brac_B_at_7907ec20.java";

		ArrayList<String> classNames1 = new ArrayList<String>();
		classNames1.add("Cl__open_brac_B_at_7907ec20");

		ArrayList<String> methodNames1 = new ArrayList<String>();
		methodNames1.add("meth__open_brac_B_at_546a03af");
		methodNames1.add("meth__open_brac_B_at_721e0f4f");
		methodNames1.add("meth__open_brac_B_at_28864e92");
		methodNames1.add("meth__open_brac_B_at_6ea6d14e");
		methodNames1.add("meth__open_brac_B_at_6ad5c04e");

		ArrayList<String> varNames1 = new ArrayList<String>();

		decryptJavaFile(fileName1, classNames1, methodNames1, varNames1,
				"aes_ecb");

		

	}

}
