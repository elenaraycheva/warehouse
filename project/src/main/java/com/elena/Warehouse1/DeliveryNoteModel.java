package com.elena.Warehouse1;



public class DeliveryNoteModel {

	private String date;
	private String supplier;
	private String operator;
	 
	 
	public DeliveryNoteModel(String date, String supplier,
			String operator) {
		super();
		this.date = date;
		this.supplier = supplier;
		this.operator = operator;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	 
	 
}
