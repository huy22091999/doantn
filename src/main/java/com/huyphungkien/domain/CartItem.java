package com.huyphungkien.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="CartItems")
public class CartItem implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	@Column
	private int quantity;
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
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

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
