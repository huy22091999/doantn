package com.huyphungkien.repository;

import com.huyphungkien.domain.CartItem;
import com.huyphungkien.model.CartItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("select c.cartId from CartItem c where c.product.productId=?1 and c.customer.customerId=?2")
    public Long getId(long productId,long customerId);
    @Query("select new com.huyphungkien.model.CartItemDto(c,true) from CartItem c where c.customer.customerId =?1")
    public List<CartItemDto> getCart(long customerId);
    @Query("select count(c.cartId) from CartItem c where c.customer.customerId =?1")
    public Integer getNumCart(long customerId);

}
