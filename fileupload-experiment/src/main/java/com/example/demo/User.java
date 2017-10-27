package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class User {
private int id;
private String name;
private String profileImageUrl;


public String getProfileImageUrl() {
	return profileImageUrl;
}
public void setProfileImageUrl(String profileImageUrl) {
	this.profileImageUrl = profileImageUrl;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
