package com.huyphungkien.controller.user;

import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.model.OrderDto;
import com.huyphungkien.service.CartItemService;
import com.huyphungkien.service.CategoryService;
import com.huyphungkien.service.OrderDetailService;
import com.huyphungkien.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("cart")
public class CartControler {
    @Autowired
    CategoryService cs;
    @Autowired
    CartItemService cis;
    @Autowired
    HttpSession session;
    @Autowired
    OrderService os;
    @Autowired
    OrderDetailService ods;

    @ModelAttribute("customerId")
    public Long getCustomerId() {
        return (Long) session.getAttribute("customerId");
    }

    @ModelAttribute("numCart")
    public Integer getNumberCart() {
        Integer num = cis.getNumCart();
        if (num == null)
            return 0;
        return num;
    }

    @ModelAttribute("categorys")
    public List<CategoryDto> listCategory() {
        return cs.findAllNotParent();
    }

    @PostMapping("add")
    public ModelAndView addCart(CartItemDto cart) {
        session.getAttribute("customerId");
        cis.save(cart);
        return new ModelAndView("forward:/product/" + cart.getProduct().getProductId());
    }

    @GetMapping("")
    public String cart(Model model) {
        List<CartItemDto> list = cis.getListCart();
        long summary = 0;
        if (list.size() > 0) {
            for (CartItemDto item : list) {
                summary += item.getQuantity() * item.getProduct().getUnitPrice();
            }
        }
        model.addAttribute("summary", summary);
        model.addAttribute("carts", list);
        return "/site/cart";
    }

    @GetMapping("/upsl/{id}")
    public ModelAndView upsl(@PathVariable("id") long id) {
        cis.upsl(id);
        return new ModelAndView("forward:/cart");
    }

    @GetMapping("/dowsl/{id}")
    public ModelAndView dowsl(@PathVariable("id") long id) {
        cis.dowsl(id);
        return new ModelAndView("forward:/cart");
    }

    @GetMapping("/payment")
    public ModelAndView payment(ModelMap model) {
        List<CartItemDto> list = cis.getListCart();
        long summary = 0;
        if (list.size() > 0) {
            for (CartItemDto item : list) {
                summary += item.getQuantity() * item.getProduct().getUnitPrice();
            }
        }
        model.addAttribute("summary", summary);
        model.addAttribute("carts", list);
        model.addAttribute("order", new OrderDto());
        return new ModelAndView("/site/order", model);
    }

}
