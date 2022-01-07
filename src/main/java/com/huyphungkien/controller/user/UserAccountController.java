package com.huyphungkien.controller.user;

import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.model.CustomerDto;
import com.huyphungkien.service.CartItemService;
import com.huyphungkien.service.CategoryService;
import com.huyphungkien.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserAccountController {
    @Autowired
    CustomerService cs;
    @Autowired
    CategoryService css;
    @Autowired
    HttpSession session;
    @Autowired
    CartItemService cis;

    @ModelAttribute("categorys")
    public List<CategoryDto> listCategory() {
        return css.findAllNotParent();
    }

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

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "/site/login";
    }

    @RequestMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "/site/sigin";
    }

    @PostMapping("/dosignin")
    public ModelAndView dosignin(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/site/sigin");
        }
        String message = null;
        CustomerDto customerDto = null;
        if (cs.checkPhone(dto.getPhone(), null)) {
            customerDto = cs.saveOrUpDate(dto);
        } else {
            message = "phone is exists!";
            model.addAttribute("message", message);
            return new ModelAndView("/site/sigin", model);
        }
        if (customerDto == null) {
            message = "Đăng kí thất bại";
        } else message = "Đăng kí thành công";
        model.addAttribute("message", message);
        return new ModelAndView("/site/login", model);
    }

    @PostMapping("/dologin")
    public ModelAndView dologin(ModelMap model, @ModelAttribute("customer") CustomerDto dto) {
        if (cs.login(dto) != null) {
            CustomerDto cus=new CustomerDto(cs.getOne(cs.login(dto)),false);
            session.setAttribute("customerName",cus.getName());
            session.setAttribute("customerId", cs.login(dto));
            Object uri = session.getAttribute("redirect-uri1");
            if (uri != null) {
                session.removeAttribute("redirect-uri1");
                return new ModelAndView("redirect:" + uri);
            }
            return new ModelAndView("forward:/");
        }
        model.addAttribute("message", "Đăng nhập thất bại!");
        return new ModelAndView("/site/login", model);
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        session.removeAttribute("customerId");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/detailacc")
    public String detailaccount(Model model) {
        CustomerDto cus = cs.getCustomer();
        model.addAttribute("custommer", cus);
        return "/site/profiledetail";
    }

    @PostMapping("/savecus")
    public ModelAndView save(ModelMap model, CustomerDto dto) {
        cs.saveOrUpDate(dto);
        model.addAttribute("message", "Cập nhật thành công!");
        return new ModelAndView("forward:/detailacc", model);
    }
}
