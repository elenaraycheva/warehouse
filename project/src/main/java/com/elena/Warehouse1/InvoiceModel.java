package com.elena.Warehouse1;

public class InvoiceModel {
	private String date;
	private String client;
	private String operator;
	 
	 
	public InvoiceModel(String date, String client,
			String operator) {
		super();
		this.date = date;
		this.client = client;
		this.operator = operator;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
