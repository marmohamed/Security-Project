package functions;

import java.sql.*;

import encrypting.DBEnc;
import DBConnection.DBConnectionInfo;

public class Register {
	
	public static int register(String email, String password) throws SQLException {
		
		   Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   String encryptedMail = DBEnc.encrptStr(email);
		   String encryptedPass = DBEnc.encrptStr(password);
           sql = "SELECT * FROM users WHERE email = '" + encryptedMail + "';";
		   ResultSet rs = stmt.executeQuery(sql);
		   if (rs.next()) {
			   System.out.println("Already exists");
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return -1;
		   } else {
			   sql = "INSERT INTO users (email, password) VALUES ('" + encryptedMail + "', '" + encryptedPass + "');";
			   stmt.executeUpdate(sql);
			   sql = "SELECT * FROM users WHERE email = '" + encryptedMail + "';";
			   rs = stmt.executeQuery(sql);
			   rs.next();
			   int id = rs.getInt("id");
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return id;
		   }
		 
	}

}
