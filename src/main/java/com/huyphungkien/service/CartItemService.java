package com.huyphungkien.service;

import com.huyphungkien.domain.CartItem;
import com.huyphungkien.model.CartItemDto;

import java.util.List;

public interface CartItemService {


    CartItem save(CartItemDto dto);

    int getNumCart();

    List<CartItemDto> getListCart();

    boolean upsl(long id);

    boolean dowsl(long id);
}
