package com.huyphungkien.model;

import com.huyphungkien.domain.CartItem;
import com.huyphungkien.domain.Customer;
import com.huyphungkien.domain.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date registeredDate;
    private short status;
    private List<OrderDto> orders;
    private List<CartItemDto> cartItems;
    public CustomerDto(){}
    public CustomerDto(Customer customer ,Boolean b ){
        this.customerId=customer.getCustomerId();
        this.name=customer.getName();
        this.email=customer.getEmail();
        this.password=customer.getPassword();
        this.phone=customer.getPhone();
        this.registeredDate=customer.getRegisteredDate();
        this.status=customer.getStatus();
        if(b==true){
            this.cartItems=new ArrayList<CartItemDto>();
            this.orders=new ArrayList<OrderDto>();
            if(customer.getCartItems()!=null &&customer.getCartItems().size()>0){
                for (CartItem c : customer.getCartItems()) {
                    cartItems.add(new CartItemDto(c,false));
                }
            }

            if(customer.getOrders()!=null&&customer.getOrders().size()>0){
                for (Order order: customer.getOrders()) {
                    orders.add(new OrderDto(order,false));
                }
            }
        }
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
