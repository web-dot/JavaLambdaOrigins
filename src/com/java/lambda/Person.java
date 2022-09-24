package com.java.lambda;

import java.time.LocalDate;
import java.time.Period;

public class Person {
	
	public enum Sex{
		MALE, FEMALE
	}
	
	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;
	
	Person(String name, LocalDate birthday, Sex gender){
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Sex getGender() {
		return gender;
	}
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public int getAge() {
		int age = 0;
		LocalDate curDate = LocalDate.now();
		if(this.birthday != null && curDate != null) {
			age = Period.between(this.birthday, curDate).getYears();
		}
		return age;
	}

	public void printPerson() {
		System.out.println("[Name: " + this.name + " Age: " + this.getAge() + "]");
	}
	
}
