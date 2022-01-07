package com.huyphungkien.service.serviceImpl;

import com.huyphungkien.domain.CartItem;
import com.huyphungkien.domain.Customer;
import com.huyphungkien.domain.Product;
import com.huyphungkien.domain.TypePro;
import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.repository.CartItemRepository;
import com.huyphungkien.repository.CustomerRepsitory;
import com.huyphungkien.repository.SizeRepository;
import com.huyphungkien.service.CartItemService;
import com.huyphungkien.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cir;
    @Autowired
    private HttpSession session;
    @Autowired
    private CustomerRepsitory cr;
    @Autowired
    SizeRepository sr;
    @Autowired
    private ProductService ps;

    @Override
    public CartItem save(CartItemDto dto) {
        if(dto!=null)
        {
            long customerId= (long) session.getAttribute("customerId");
            CartItem entity=null;
            Long id=cir.getId(dto.getProduct().getProductId(),customerId);
            Product product=ps.getOne(dto.getProduct().getProductId());
            if(id!=null)
            {
                entity=cir.getOne(id);
                entity.setQuantity(entity.getQuantity()+dto.getQuantity());

            }else {
                entity=new CartItem();
                entity.setQuantity(dto.getQuantity());

                entity.setProduct(product);
                Customer customer=cr.getOne(customerId);
                entity.setCustomer(customer);
            }
            if(product.getSizes()!=null&&product.getSizes().size()>0&&dto.getSize()!=null&&dto.getSize().getSizeId()!=null){
                TypePro size=sr.getOne(dto.getSize().getSizeId());
                entity.setSize(size);
            }
            entity=cir.save(entity);
            if(entity!=null)
            {
                return entity;
            }
        }
        return null;
    }
    @Override
    public int getNumCart()
    {
        if(session.getAttribute("customerId")!=null)
            return cir.getNumCart((Long) session.getAttribute("customerId"));
        return 0;
    }
    @Override
    public List<CartItemDto> getListCart(){
        return cir.getCart((Long) session.getAttribute("customerId"));
    }
    @Override
    public boolean upsl(long id){
        CartItem cart=cir.getOne(id);
        try {
            cart.setQuantity(cart.getQuantity()+1);
            cir.save(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public boolean dowsl(long id){
        CartItem cart=cir.getOne(id);
        try {
            if(cart.getQuantity()>1){
                cart.setQuantity(cart.getQuantity()-1);
                cir.save(cart);
            }else cir.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
