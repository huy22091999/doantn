package com.huyphungkien.service;

import com.huyphungkien.domain.Order;
import com.huyphungkien.model.OrderDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDto> getList(Integer status);

    List<Order> findAll();

    @Deprecated
    Order getOne(Long aLong);

    Order getById(Long aLong);

    OrderDto save(OrderDto dto);

    Optional<Order> findById(Long aLong);

    boolean existsById(Long aLong);

    void deleteById(Long aLong);

    @Query("select new com.dungngocshop.model.OrderDto(o,true) from Order o where o.status=?1")
    List<OrderDto> adminGetList(Integer status);

    @Query("select new com.dungngocshop.model.OrderDto(o,true) from Order o ")
    List<OrderDto> adminGetList();

    OrderDto confim(Long id);

    OrderDto tran(Long id);

    OrderDto traned(Long id);

    OrderDto cancel(Long id);
}
