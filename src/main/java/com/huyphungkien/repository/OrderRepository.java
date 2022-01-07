package com.huyphungkien.repository;

import com.huyphungkien.domain.Order;
import com.huyphungkien.model.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select new com.huyphungkien.model.OrderDto(o,true) from Order o where o.customer.customerId=?1 and o.status=?2  ")
    public List<OrderDto> getList(Long customerId,Integer status);
    @Query("select new com.huyphungkien.model.OrderDto(o,true) from Order o where o.customer.customerId=?1  ")
    public List<OrderDto> getList(Long customerId);
    @Query("select new com.huyphungkien.model.OrderDto(o,true) from Order o where o.status=?1")
    public List<OrderDto> adminGetList(Integer status);
    @Query("select new com.huyphungkien.model.OrderDto(o,true) from Order o ")
    public List<OrderDto> adminGetList();
}
