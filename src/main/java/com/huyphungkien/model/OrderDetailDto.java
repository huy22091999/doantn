package com.huyphungkien.model;

import com.huyphungkien.domain.OrderDetail;

public class OrderDetailDto {
    private long oderDetailId;
    private int quantity;
    private long unitPrice;
    private OrderDto oder;
    private ProductDto product;
    private TypeProDto size;
    public OrderDetailDto(){}

    public TypeProDto getSize() {
        return size;
    }

    public void setSize(TypeProDto size) {
        this.size = size;
    }

    public OrderDetailDto(OrderDetail detail, Boolean b){
        this.oderDetailId=detail.getOderDetailId();
        this.quantity=detail.getQuantity();
        this.unitPrice=detail.getUnitPrice();
        if(b==true)
        {
            if(detail.getOder()!=null){
                this.oder=new OrderDto(detail.getOder(),false);
            }
            if(detail.getProduct()!=null){
                this.product=new ProductDto(detail.getProduct(),false);
            }
        }
        if(detail.getSize()!=null){
            this.size=new TypeProDto(detail.getSize(),false);
        }
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

    public OrderDto getOder() {
        return oder;
    }

    public void setOder(OrderDto oder) {
        this.oder = oder;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
