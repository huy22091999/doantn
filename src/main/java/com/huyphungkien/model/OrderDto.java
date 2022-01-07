package com.huyphungkien.model;

import com.huyphungkien.domain.Order;
import com.huyphungkien.domain.OrderDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private long oderId;
    private Date oderDate;
    private int amount;
    private int status;
    private Date finishDate;
    private String phone;
    private String adress;
    private String note;
    private String name;
    private CustomerDto customer;
    private List<OrderDetailDto> oderDetails;
    private long summary;
    private long ship;
    public long getShip() {
        return ship;
    }
    public void setShip(long ship) {
        this.ship = ship;
    }
    public OrderDto(){}

    public long getSummary() {
        return summary;
    }

    public OrderDto(Order order, Boolean b){
        this.oderId=order.getOderId();
        this.oderDate=order.getOderDate();
        this.amount=order.getAmount();
        this.status=order.getStatus();
        this.finishDate=order.getFinishDate();
        this.phone=order.getPhone();
        this.adress=order.getAdress();
        this.note=order.getNote();
        this.name=order.getName();
        this.summary=0;
        if(b==true){
            if(order.getCustomer()!=null){
                this.customer=new CustomerDto(order.getCustomer(),false);
            }
            this.oderDetails=new ArrayList<OrderDetailDto>();
            if(order.getOderDetails()!=null&&order.getOderDetails().size()>0){
                for (OrderDetail o:order.getOderDetails()) {
                    this.oderDetails.add(new OrderDetailDto(o,true));
                    this.summary +=o.getUnitPrice();
                }
                if(summary<500000)
                    this.ship=30000;
            }
        }
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<OrderDetailDto> getOderDetails() {
        return oderDetails;
    }

    public void setOderDetails(List<OrderDetailDto> oderDetails) {
        this.oderDetails = oderDetails;
    }

    public long getOderId() {
        return oderId;
    }

    public void setOderId(long oderId) {
        this.oderId = oderId;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setSummary(long summary) {
        this.summary = summary;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
