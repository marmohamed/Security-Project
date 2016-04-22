package functions;

import java.sql.*;

import DBConnection.DBConnectionInfo;


public class Cl__open_brac_B_at_2b80d80f {
	
	public static boolean meth__open_brac_B_at_3ab39c39(int userId) throws SQLException {
		
		   Connection conn = DBConnectionInfo.createConnection();
		   Statement stmt = null;
		   
		   //STEP 4: Execute a query
		   System.out.println("Creating statement...");
		   
		   stmt = conn.createStatement();
		   String sql;
		   sql = "DELETE * FROM users WHERE id = " + String.valueOf(userId);
		   stmt.executeUpdate(sql);
		   
			 
			   if (stmt != null) {
				   stmt.close();
			   }
			   DBConnectionInfo.CloseConnection();
			   return true;
		   
	}
	
	
}
