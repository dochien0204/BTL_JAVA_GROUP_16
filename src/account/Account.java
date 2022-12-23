package account;

import java.io.Serializable;

public class Account implements Serializable{
	
	private static final long serialVersionUID = 6746124924216314259L;

	private String userName;

	private String password;

	public Account() {

	}

	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + "]";
	}

}
