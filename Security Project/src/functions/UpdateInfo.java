package functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import encrypting.DBEnc;
import DBConnection.DBConnectionInfo;

public class UpdateInfo {
	
	public static boolean updateEmail(String email, int  id) throws SQLException {
		
		Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   String encryptedMail = DBEnc.encrptStr(email);
		   sql = "SELECT * FROM users WHERE email = '" + encryptedMail + "';";
		   ResultSet rs = stmt.executeQuery(sql);
		   if (rs.next()) {
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return false;
		   } else {
			   sql = "UPDATE users SET email = '" + encryptedMail + "' WHERE id = " + String.valueOf(id) + ";";
			   stmt.executeUpdate(sql);
			   if (rs != null) {
				   rs.close();
			   }
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
		   }
		
	}
	
	public static boolean updatePassword(String password, int id) throws SQLException {
		Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   String encryptedPass = DBEnc.encrptStr(password);
			   sql = "UPDATE users SET password = '" + encryptedPass + "' WHERE id = " + String.valueOf(id) + ";";
			   stmt.executeUpdate(sql);
			   
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
		   
	}
	
	public static boolean updateFirstName(String fname, int id) throws SQLException {
		Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   
			   sql = "UPDATE users SET first_name = '" + fname + "' WHERE id = " + String.valueOf(id) +  ";";
			   stmt.executeUpdate(sql);
			   
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
	}
	
	public static boolean updateLastName(String lname, int id) throws SQLException {
		Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   
			   sql = "UPDATE users SET last_name = '" + lname + "' WHERE id = " + String.valueOf(id) + ";";
			   stmt.executeUpdate(sql);
			   
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
	}
	
	public static boolean updateAge(int age, int id) throws SQLException {
		Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   
			   sql = "UPDATE users SET age = " + age + " WHERE id = " + String.valueOf(id) + ";";
			   stmt.executeUpdate(sql);
			   
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
	}

}
