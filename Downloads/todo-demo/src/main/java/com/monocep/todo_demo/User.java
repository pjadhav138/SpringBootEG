package com.monocep.todo_demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name= "UserTable")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2 , message="Username Should have atleast 2 characters.")
	private String username;
	@Size(min=8 ,message="Password should contain atleast 8 characters.")
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	
}
	