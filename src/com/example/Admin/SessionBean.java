package com.example.Admin;

public class SessionBean {
	private String userName=null,userType=null,companyName=null,developerAddress=null,companyAddress=null;
	public String getUserName(){
		return userName;
	}
	public void setUserName(String username){
		this.userName=username;
	}
	public String getUserType(){
		return userType;
	}
	public void setUserType(String userType){
		this.userType=userType;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	
	public String getDevloperAddress(){
		return developerAddress;
	}
	public void setDeveloperAddress(String devloperAddress){
		this.developerAddress=devloperAddress;
	}
	
	public String getCompanyAddress(){
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress){
		this.companyAddress=companyAddress;
	}

}
