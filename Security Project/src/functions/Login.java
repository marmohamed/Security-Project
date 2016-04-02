package functions;

import java.sql.*;

import encrypting.DBEnc;
import DBConnection.DBConnectionInfo;

public class Login {
	
	public static int login(String email, String password) throws SQLException {
		
		   Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   String encryptedMail = DBEnc.encrptStr(email);
		   String encryptedPass = DBEnc.encrptStr(password);
		   System.out.println(encryptedMail);
		   System.out.println(encryptedPass);
		   sql = "SELECT * FROM users WHERE email = '" + encryptedMail + "' AND password = '" + encryptedPass + "';";
		   ResultSet rs = stmt.executeQuery(sql);
		   if (rs.next()) {
			   int id = rs.getInt("id");
			   System.out.println("Logged in with id = " + id);
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return id;
		   } else {
			   System.out.println("Can not log in");
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return -1;
		   }
		   
		 
	}

}
