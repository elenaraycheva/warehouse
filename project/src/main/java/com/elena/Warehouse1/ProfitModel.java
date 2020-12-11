package com.elena.Warehouse1;

public class ProfitModel {
	
	private double income;
	private double expence;
	private double profit;
	private String cashRegister;
	
	
	
	public ProfitModel(double income, double expence, double profit, String cashRegister) {
		super();
		this.income = income;
		this.expence = expence;
		this.profit = profit;
		this.cashRegister = cashRegister;
	}
	
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getExpence() {
		return expence;
	}
	public void setExpence(double expence) {
		this.expence = expence;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(String cashRegister) {
		this.cashRegister = cashRegister;
	}
	
	
}
