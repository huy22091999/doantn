package com.huyphungkien.controller.admin;

import com.huyphungkien.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    CustomerService cs;
    @GetMapping("")
    public String getlist(Model model){
        model.addAttribute("list",cs.getAll());
        return "/admin/listcustomer";
    }
}
