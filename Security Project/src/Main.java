import java.sql.SQLException;

import encrypting.DBEnc;
import models.User;


public class Main {

	
	public static void main(String[] args) throws SQLException {
		User user1 = new User();
		user1.setEmail("maryam3@gmail.com");
		user1.setPassword("333");
		
		User user2 = new User();
		user2.setEmail("sara@gmail.com");
		user2.setPassword("11111");
		
		user2.register();
		
		int userLogin = user1.login();
		
		if (userLogin != -1) {
			System.out.println("Succefully logged in.");
			user1.setId(userLogin);
		}
		else {
			System.out.println("Failed to log in.");
		}
		System.out.println(".......");
		int userRegister = user1.register();
		if (userRegister != -1) {
			System.out.println("Succefully registered.");
			user1.setId(userRegister);
		}
		else {
			System.out.println("Failed to register.");
		}
		System.out.println(".......");
		userLogin = user1.login();
		if (userLogin != -1) {
			System.out.println("Succefully logged in.");
		}
		else {
			System.out.println("Failed to log in.");
		}
		
		
		boolean upFName = user1.updateFirstName("Mariam2");
		
		boolean upLName = user1.updateLastName("Mohamed");
		
		boolean upAge = user1.updateAge(22);
		
		boolean upPass = user1.updatePassword("333");
		
		boolean upEmail = user1.updateEmail("sara@gmail.com");
		
		System.out.println("upFName = " + String.valueOf(upFName));
		
		System.out.println("upLName = " + String.valueOf(upLName));
		
		System.out.println("upAge = " + String.valueOf(upAge));
		
		System.out.println("upPass = " + String.valueOf(upPass));
		
		System.out.println("upEmail = " + String.valueOf(upEmail));
		
		
		
		
		
	}
	
	
//	public static void main(String[] args) {
//		String name = "Mariam";
//		
//		String enc = DBEnc.encrptStr(name);
//		System.out.println(enc);
//		String dec = DBEnc.decreptStr(enc);
//		System.out.println(dec);
//		String enc2 = DBEnc.encrptStr(name);
//		System.out.println(enc2);
//	}
//	
	
	
	
	
	
}
