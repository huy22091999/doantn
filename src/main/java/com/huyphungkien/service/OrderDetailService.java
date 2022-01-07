package com.huyphungkien.service;

import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.model.OrderDetailDto;
import com.huyphungkien.model.OrderDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> save(List<CartItemDto> list, OrderDto orderDto);
}
