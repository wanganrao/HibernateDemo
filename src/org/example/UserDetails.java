package org.example;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {

	@Id
	String userId;
	String userName;
	Set certs;
	
	
	public Set getCerts() {
		return certs;
	}
	public void setCerts(Set certs) {
		this.certs = certs;
	}
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
	
	
}
