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
@Table(name="delivery_note_items")
public class DeliveryNoteItems implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id", unique = true)
	private int id;
	
	@Column(name="amount_delivery", nullable = false)
	private double amountDelivery;

	@ManyToOne
	@JoinColumn(name="stock_id")
	private Stocks stock;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="delivery_note_id")
	private DeliveryNotes deliveryNote;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getAmountDelivery() {
		return amountDelivery;
	}

	public void setAmountDelivery(double amountDelivery) {
		this.amountDelivery = amountDelivery;
	}

	public Stocks getStock() {
		return stock;
	}

	public void setStock(Stocks stock) {
		this.stock = stock;
	}

	public DeliveryNotes getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(DeliveryNotes deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	
	
}
