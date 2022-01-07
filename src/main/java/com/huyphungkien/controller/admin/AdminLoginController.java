package com.huyphungkien.controller.admin;

import com.huyphungkien.model.AccountDto;
import com.huyphungkien.service.AccountService;
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

@Controller
public class AdminLoginController {
    @Autowired
    private AccountService as;
    @Autowired
    private HttpSession session;

    @RequestMapping("/adminlogin")
    public String login(Model model) {
        model.addAttribute("dto", new AccountDto());
        return "/admin/account/login";
    }

    @PostMapping("/admindologin")
    public ModelAndView doLogin(ModelMap model, @Valid @ModelAttribute("dto") AccountDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/admin/account/login", model);
        }
        boolean t = as.login(dto);
        if (t) {
            session.setAttribute("username", dto.getUserName());
            Object uri = session.getAttribute("redirect-uri");
            if (uri != null) {
                session.removeAttribute("redirect-uri");
                return new ModelAndView("redirect:" + uri);
            }
            return new ModelAndView("forward:/admin");
        } else {
            model.addAttribute("message", "user is vaild!");
            return new ModelAndView("/admin/account/login", model);
        }
    }
    @RequestMapping("/adminlogout")
    public ModelAndView logout(ModelMap model){
        session.removeAttribute("username");
        return new ModelAndView("redirect:/adminlogin");
    }
    @GetMapping("/admin")
    public String homeAdmin(){
        return "/admin/home";
    }

}
