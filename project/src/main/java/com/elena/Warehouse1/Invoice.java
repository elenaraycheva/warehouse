package com.elena.Warehouse1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_invoice", unique = true)
	private int id;
	
	@Column(name="ddate", nullable = false)
	private String date;
	
	@Column(name="total_price", nullable = false)
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="clients_id")
	private Clients client;

	@ManyToOne
	@JoinColumn(name="operator_id")
	private Operators operator;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.PERSIST)
	List<InvoiceItems> invoiceItems = new ArrayList<InvoiceItems>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public List<InvoiceItems> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItems> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
