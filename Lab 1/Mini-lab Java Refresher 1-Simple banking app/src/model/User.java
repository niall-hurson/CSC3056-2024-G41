package model;

public class User {
 private String username; //email address
 private String password;
 private String first_name;
 private String last_name;
 private String mobile_number;
 
public String getUsername() {
	return username;
}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getMobile_number() {
		return mobile_number;
	}
	
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public User(String username, String password, String first_name, String last_name, String mobile_number) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_number = mobile_number;
	}
	
	public String toString() {
		return username + ", " + password + ", " + first_name + ", " + ", " + last_name + ", " + mobile_number;
	}
	
	
}
