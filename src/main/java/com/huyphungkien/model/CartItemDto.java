package com.huyphungkien.model;

import com.huyphungkien.domain.CartItem;

public class CartItemDto {
    private long cartId;
    private int quantity;
    private CustomerDto customer;
    private ProductDto product;
    private TypeProDto size;
    public CartItemDto(){}
    public CartItemDto(CartItem entity,Boolean b){
        this.cartId=entity.getCartId();
        this.quantity=entity.getQuantity();
        if(b==true){
            if(entity.getCustomer()!=null){
                this.customer=new CustomerDto(entity.getCustomer(),false);
            }
        }
        if (entity.getProduct()!=null){
            this.product=new ProductDto(entity.getProduct(),false);
        }
        if(entity.getSize()!=null){
            this.size=new TypeProDto(entity.getSize(),false);
        }
    }

    public TypeProDto getSize() {
        return size;
    }

    public void setSize(TypeProDto size) {
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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
