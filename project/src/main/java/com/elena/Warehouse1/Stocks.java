package com.elena.Warehouse1;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stocks")
public class Stocks implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_stock", unique = true)
	private int id;
	
	@Column(name="amount", nullable = false)
	private double amount;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id")
	private Brands brand;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="type_id")
	private Type type;
	
	@Column(name="price_for_buying", nullable = false)
	private double priceForBuying;

	public int getId() {
		return id;
	}

	public double getPriceForBuying() {
		return priceForBuying;
	}

	public void setPriceForBuying(double priceForBuying) {
		this.priceForBuying = priceForBuying;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

}
