package com.infy.dto;

public class CustomerLoginDTO {
	private String loginname;
	private String password;
	
	public String getLoginName() {
		return loginname;
	}
	public void setLoginName(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "CustomerLoginDTO [loginname=" + loginname + ", password=" + password + "]";
	}

}
