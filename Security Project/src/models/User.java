package models;

import java.sql.SQLException;

import functions.Deactivate;
import functions.Login;
import functions.Register;
import functions.UpdateInfo;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int register() throws SQLException {
		return Register.register(email, password);
	}
	
	
	public int login() throws SQLException {
		return Login.login(email, password);
	}
	
	
	public boolean updateEmail(String mail) {
		try {
			return UpdateInfo.updateEmail(mail, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updatePassword(String pass) {
		try {
			return UpdateInfo.updatePassword(pass, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateFirstName(String fname) {
		try {
			return UpdateInfo.updateFirstName(fname, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateLastName(String lname) {
		try {
			return UpdateInfo.updateLastName(lname, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateAge(int uAge) {
		try {
			return UpdateInfo.updateAge(uAge, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deactivate() throws SQLException {
		return Deactivate.deactivate(id);
	}
	
	
	
	
	
	
	
	
}
