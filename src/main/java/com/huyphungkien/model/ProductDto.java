package com.huyphungkien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.huyphungkien.domain.Image;
import com.huyphungkien.domain.Product;
import com.huyphungkien.domain.TypePro;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto implements Serializable{
	private Long productId;
	private String name;
	private int quantity;
	private long unitPrice;
	private List<ImageDto> images;
	private MultipartFile[] files;
	private String description;
	private double discount;
	private Date enteredDate;
	private int status;
	private CategoryDto category;
	private List<TypeProDto> sizes;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<TypeProDto> getSizes() {
		return sizes;
	}

	public void setSizes(List<TypeProDto> sizes) {
		this.sizes = sizes;
	}

	public List<ImageDto> getImages() {
		return images;
	}

	public void setImages(List<ImageDto> images) {
		this.images = images;
	}
	public ProductDto(){}
	public ProductDto(Product p,Boolean b){
		this.productId=p.getProductId();
		this.name=p.getName();
		this.quantity=p.getQuantity();
		this.unitPrice=p.getUnitPrice();
		this.description=p.getDescription();
		this.discount=p.getDiscount();
		this.enteredDate=p.getEnteredDate();
		this.status=p.getStatus();
		if(p.getCategory()!=null){
			this.category=new CategoryDto(p.getCategory(),false);
		}
//		if(b==true)
//		{
//			this.orderDetails=new ArrayList<OrderDetailDto>();
//			if(p.getOderDetails()!=null&&p.getOderDetails().size()>0)
//			{
//				for (OrderDetail item:p.getOderDetails()) {
//					this.orderDetails.add(new OrderDetailDto(item,false));
//				}
//			}
//		}
		this.images=new ArrayList<ImageDto>();
		if(p.getImages()!=null&&p.getImages().size()>0){
			for (Image i:p.getImages()) {
				this.images.add(new ImageDto(i));
			}
		}
		this.sizes=new ArrayList<TypeProDto>();
		if(p.getSizes()!=null&&p.getSizes().size()>0){
			for (TypePro s:p.getSizes()) {
				this.sizes.add(new TypeProDto(s,false));
			}
		}
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
}
