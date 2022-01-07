package com.huyphungkien.service.serviceImpl;

import com.huyphungkien.domain.Customer;
import com.huyphungkien.domain.Order;
import com.huyphungkien.domain.OrderDetail;
import com.huyphungkien.model.OrderDetailDto;
import com.huyphungkien.model.OrderDto;
import com.huyphungkien.repository.CustomerRepsitory;
import com.huyphungkien.repository.OrderDetailRepository;
import com.huyphungkien.repository.OrderRepository;
import com.huyphungkien.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    HttpSession session;
    @Autowired
    OrderRepository or;
    @Autowired
    CustomerRepsitory cr;
    @Autowired
    OrderDetailRepository odr;

    @Override
    public List<OrderDto> getList(Integer status) {
        Long id = (Long) session.getAttribute("customerId");
        if (id != null) {
            if (status != null) {
                return or.getList(id, status);
            }
            return or.getList(id);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return or.findAll();
    }

    @Override
    @Deprecated
    public Order getOne(Long aLong) {
        return or.getOne(aLong);
    }

    @Override
    public Order getById(Long aLong) {
        return or.getById(aLong);
    }

    @Override
    public OrderDto save(OrderDto dto) {
        if (dto != null) {
            Customer customer = cr.getOne((Long) session.getAttribute("customerId"));
            Order order = new Order();
            if (customer != null) {
                order.setCustomer(customer);
            }
            order.setOderId(dto.getOderId());
            Date date = new Date();
            order.setOderDate(date);
            order.setStatus(1);
            order.setPhone(dto.getPhone());
            order.setAdress(dto.getAdress());
            order.setNote(dto.getNote());
            order.setName(dto.getName());
            if (dto.getOderDetails() != null && dto.getOderDetails().size() > 0) {
                List<OrderDetail> list = new ArrayList<OrderDetail>();
                for (OrderDetailDto item : dto.getOderDetails()) {
                    OrderDetail entity = odr.getOne(item.getOderDetailId());
                    list.add(entity);
                }
                order.setAmount(dto.getOderDetails().size());
            }

            order = or.save(order);
            if (order != null) {
                return new OrderDto(order, true);
            }
        }
        return null;
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return or.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return or.existsById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        or.deleteById(aLong);
    }

    @Override
    @Query("select new com.dungngocshop.model.OrderDto(o,true) from Order o where o.status=?1")
    public List<OrderDto> adminGetList(Integer status) {
        return or.adminGetList(status);
    }

    @Override
    @Query("select new com.dungngocshop.model.OrderDto(o,true) from Order o ")
    public List<OrderDto> adminGetList() {
        return or.adminGetList();
    }

    @Override
    public OrderDto confim(Long id){
        Order order=or.getOne(id);
        if(order!=null){
            order.setStatus(order.getStatus()+1);
            order=or.save(order);
            return new OrderDto(order,true);
        }
        return null;
    }
    @Override
    public OrderDto tran(Long id){
        Order order=or.getOne(id);
        if(order!=null){
            order.setStatus(order.getStatus()+1);
            order=or.save(order);
            return new OrderDto(order,true);
        }
        return null;
    }
    @Override
    public OrderDto traned(Long id){
        Order order=or.getOne(id);
        if(order!=null){
            order.setStatus(order.getStatus()+1);
            order.setFinishDate(new Date());
            order=or.save(order);
            return new OrderDto(order,true);
        }
        return null;
    }
    @Override
    public OrderDto cancel(Long id){
        Order order=or.getOne(id);
        if(order!=null){
            order.setStatus(0);
            order=or.save(order);
            return new OrderDto(order,true);
        }
        return null;
    }

}
