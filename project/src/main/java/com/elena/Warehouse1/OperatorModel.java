package com.elena.Warehouse1;

public class OperatorModel {
	
	private String firstName;
	private String lastName;
	private String date;
	private String part;
	
	
	
	public OperatorModel(String firstName, String lastName, String date, String part) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.part = part;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getPart() {
		return part;
	}
	
	public void setPart(String part) {
		this.part = part;
	}	

}
