package com.huyphungkien.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(columnDefinition = "nvarchar(100) not null")
	private String name;
	@Column(nullable = false)
	private Integer quantity;
	@Column(nullable = false)
	private long unitPrice;
	@Type(type = "text")
	private String description;
	@Column(nullable = false)
	private double discount;
	@Temporal(TemporalType.DATE)
	private Date enteredDate;
	@Column(nullable = false)
	private int status;
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private Set<TypePro> sizes;
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private Set<Image> images;

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	//	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
//	private Set<OrderDetail> oderDetails;

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Set<TypePro> getSizes() {
		return sizes;
	}

	public void setSizes(Set<TypePro> sizes) {
		this.sizes = sizes;
	}



	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public Set<OrderDetail> getOderDetails() {
//		return oderDetails;
//	}
//
//	public void setOderDetails(Set<OrderDetail> oderDetails) {
//		this.oderDetails = oderDetails;
//	}

	
}
