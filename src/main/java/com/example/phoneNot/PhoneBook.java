package com.example.phoneNot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhoneBook {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;

private String name;

private String surname;

private String phoneNumber;




public PhoneBook() {
	super();
}




public PhoneBook(String name, String surname, String phoneNumber) {
	super();
	this.name = name;
	this.surname = surname;
	this.phoneNumber = phoneNumber;
}




public PhoneBook(long id, String name, String surname, String phoneNumber) {
	super();
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.phoneNumber = phoneNumber;
}




public long getId() {
	return id;
}




public void setId(long id) {
	this.id = id;
}




public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public String getSurname() {
	return surname;
}




public void setSurname(String surname) {
	this.surname = surname;
}




public String getPhoneNumber() {
	return phoneNumber;
}




public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}





}
