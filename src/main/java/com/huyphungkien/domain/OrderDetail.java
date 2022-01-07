package com.huyphungkien.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OderDetails")
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oderDetailId;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private long unitPrice;
	@ManyToOne
	@JoinColumn(name="oderId")
	private Order oder;
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "sizeId")
	private TypePro size;

	public TypePro getSize() {
		return size;
	}

	public void setSize(TypePro size) {
		this.size = size;
	}

	public long getOderDetailId() {
		return oderDetailId;
	}

	public void setOderDetailId(long oderDetailId) {
		this.oderDetailId = oderDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOder() {
		return oder;
	}

	public void setOder(Order oder) {
		this.oder = oder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
