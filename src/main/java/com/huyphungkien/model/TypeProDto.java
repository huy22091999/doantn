package com.huyphungkien.model;

import com.huyphungkien.domain.TypePro;

public class TypeProDto {
    private Long sizeId;
    private String name;
    private int quantity;
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    public TypeProDto() {
    }
    public TypeProDto(TypePro size, Boolean b) {
        this.sizeId=size.getSizeId();
        this.name=size.getName();
        this.quantity=size.getQuantity();
        this.price=size.getPrice();
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
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
}
