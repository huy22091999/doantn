package com.huyphungkien.controller.user;

import com.huyphungkien.domain.Order;
import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.model.OrderDetailDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService os;
    @Autowired
    CartItemService cis;
    @Autowired
    OrderDetailService ods;
    @Autowired
    CategoryService cs;
    @Autowired
    HttpSession session;

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
    @ModelAttribute("customerName")
    public String customerName(){
        return (String) session.getAttribute("customerName");
    }

    @ModelAttribute("categorys")
    public List<CategoryDto> listCategory() {
        return cs.findAllNotParent();
    }

    @PostMapping("")
    public ModelAndView order(ModelMap model, OrderDto orderDto) {
        List<CartItemDto> list = cis.getListCart();
        orderDto = os.save(orderDto);
        List<OrderDetailDto> l = ods.save(list, orderDto);
        orderDto.setOderDetails(l);
        orderDto=os.save(orderDto);
        model.addAttribute("orderId",orderDto.getOderId());
        return new ModelAndView("/site/orderfinish",model);
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id ,Model model){
        Optional<Order> order=os.findById(id);
        if(order.isPresent())
        {
            model.addAttribute("order",new OrderDto(order.get(),true));
        }
        else model.addAttribute("message","Không tìm thấy thông tin đơn hàng!");
        return "/site/detailorder";
    }
    @GetMapping("/manageorder")
    public String manageOrder(Model model) {
        List<OrderDto> list = os.getList(null);
        model.addAttribute("list", list);

        List<OrderDto> list1 = os.getList(1);
        model.addAttribute("list1", list1);

        List<OrderDto> list2 = os.getList(2);
        model.addAttribute("list2", list2);

        List<OrderDto> list3 = os.getList(3);
        model.addAttribute("list3", list3);

        List<OrderDto> list4 = os.getList(4);
        model.addAttribute("list4", list4);

        List<OrderDto> list0 = os.getList(0);
        model.addAttribute("list0", list0);

        return "/site/manageorder";
    }
    @GetMapping("/cancel/{id}")
    public ModelAndView cancel(@PathVariable(name = "id") Long id){
        os.cancel(id);
        return new ModelAndView("forward:/order/manageorder");
    }

    @GetMapping("/rebuy/{id}")
    public ModelAndView rebuy(@PathVariable(name = "id") Long id){

        return new ModelAndView("forward:/order/manageorder");
    }

}
