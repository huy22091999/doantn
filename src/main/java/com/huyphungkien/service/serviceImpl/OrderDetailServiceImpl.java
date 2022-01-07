package com.huyphungkien.service.serviceImpl;

import com.huyphungkien.domain.Order;
import com.huyphungkien.domain.OrderDetail;
import com.huyphungkien.domain.Product;
import com.huyphungkien.domain.TypePro;
import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.model.OrderDetailDto;
import com.huyphungkien.model.OrderDto;
import com.huyphungkien.repository.*;
import com.huyphungkien.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    CartItemRepository cir;
    @Autowired
    OrderDetailRepository odr;
    @Autowired
    ProductRepository pr;
    @Autowired
    OrderRepository or;
    @Autowired
    SizeRepository sr;

    @Override
    public List<OrderDetailDto>  save(List<CartItemDto> list, OrderDto orderDto) {
        List<OrderDetailDto> listDto=new ArrayList<OrderDetailDto>();
        if(list!=null&&list.size()>0){
            for (CartItemDto item:list) {
                OrderDetail detail=new OrderDetail();
                Product product=pr.getOne(item.getProduct().getProductId());
                detail.setProduct(product);
                detail.setQuantity(item.getQuantity());
                detail.setUnitPrice(item.getQuantity()*product.getUnitPrice());
                if(orderDto!=null){
                    Order order=or.getOne(orderDto.getOderId());
                    detail.setOder(order);
                }
                if(item.getSize()!=null&&item.getSize().getSizeId()!=null){
                    TypePro size=sr.getOne(item.getSize().getSizeId());
                    detail.setSize(size);
                }
                detail=odr.save(detail);
                cir.deleteById(item.getCartId());
                listDto.add(new OrderDetailDto(detail,true));
            }
            return listDto;
        }
        return null;
    }
}
