package com.community.auth.dto;


public class AuthRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    public AuthRequest(String name, String email, String phoneNumber, String password, String role,
			String communityAccessCode) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
		this.communityAccessCode = communityAccessCode;
	}
	private String role;
    private String communityAccessCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCommunityAccessCode() {
		return communityAccessCode;
	}
	public void setCommunityAccessCode(String communityAccessCode) {
		this.communityAccessCode = communityAccessCode;
	}
	public AuthRequest() {
		super();
	}
    
    
}