package com.huyphungkien.controller.admin;

import com.huyphungkien.domain.Order;
import com.huyphungkien.model.OrderDto;
import com.huyphungkien.service.CategoryService;
import com.huyphungkien.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/order")
public class ManageOrderController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService os;
    @GetMapping("")
    public String  list(Model model)
    {
        model.addAttribute("list",os.adminGetList());
        model.addAttribute("list1",os.adminGetList(1));
        model.addAttribute("list2",os.adminGetList(2));
        model.addAttribute("list3",os.adminGetList(3));
        model.addAttribute("list4",os.adminGetList(4));
        model.addAttribute("list0",os.adminGetList(0));
        return "/admin/managebill";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(name = "id") Long id,  Model model){
        Order order=os.getOne(id);
        if(order!=null){
            model.addAttribute("bill",new OrderDto(order,true));
        }
        else model.addAttribute("message","không tìm thấy thông tin đơn hàng!!");
        return "/admin/billdetail";
    }
    @GetMapping("/confim/{id}")
    public ModelAndView confim(@PathVariable(name = "id")Long id, ModelMap model){
        os.confim(id);
        return new ModelAndView("redirect:/admin/order");
    }
    @GetMapping("/tran/{id}")
    public ModelAndView tran(@PathVariable(name = "id")Long id, ModelMap model){
        os.tran(id);
        return new ModelAndView("redirect:/admin/order");
    }
    @GetMapping("/traned/{id}")
    public ModelAndView traned(@PathVariable(name = "id")Long id, ModelMap model){
        os.traned(id);
        return new ModelAndView("redirect:/admin/order");
    }

}
