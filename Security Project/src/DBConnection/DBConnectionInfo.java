package DBConnection;

import java.sql.*;

public class DBConnectionInfo {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/SecUsers";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "mypass";
	
	static Connection conn;
	   
	public static Connection createConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					//Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//Open a connection
					System.out.println("Connecting to database...");
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void CloseConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   

}
