package com.shopdirect.aims.model;

public class User {
	
	private String userId;
	private String userName;
	private String cloneId;
	private boolean status;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCloneId() {
		return cloneId;
	}
	public void setCloneId(String cloneId) {
		this.cloneId = cloneId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", cloneId=" + cloneId + ", status=" + status
				+ "]";
	}	
}
