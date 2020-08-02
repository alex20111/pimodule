package net.pi.pimodule;

public class User {
	
	private String access = "";
	private String authToken = "";
	private String userName = "";
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User [access=" + access + ", authToken=" + authToken + ", userName=" + userName + "]";
	}
	
	

}
