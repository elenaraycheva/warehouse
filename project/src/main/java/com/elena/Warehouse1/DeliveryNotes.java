package com.elena.Warehouse1;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="delivery_notes")
public class DeliveryNotes implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_delivery", unique = true)
	private int id;
	
	@Column(name="ddate", nullable = false)
	private String date;
	
	@Column(name="total_price", nullable = false)
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name="operator_id")
	private Operators operator;
	
	@OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.PERSIST)
	List<DeliveryNoteItems> deliveryNoteItems;
	
	

	public List<DeliveryNoteItems> getDeliveryNoteItems() {
		return deliveryNoteItems;
	}

	public void setDeliveryNoteItems(List<DeliveryNoteItems> deliveryNoteItems) {
		this.deliveryNoteItems = deliveryNoteItems;
	}

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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
