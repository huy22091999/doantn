package com.huyphungkien.controller.admin;

import com.huyphungkien.domain.Account;
import com.huyphungkien.model.AccountDto;
import com.huyphungkien.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

    @Autowired
    private AccountService as;
    @GetMapping("/add")
    public String add(Model model){
        AccountDto dto=new AccountDto();
        model.addAttribute("account",dto);
        return"/admin/account/addOrEdit";
    }
    @RequestMapping("")
    public String list(Model model){
       List<Account> list=as.findAll();
       model.addAttribute("list",list);
       return "/admin/account/list";
    }
    @PostMapping("/saveorupdate")
    public ModelAndView saveOrUpDate(ModelMap model,AccountDto dto)
    {
        Account accout=new Account();
        BeanUtils.copyProperties(dto,accout);String message;
        if(as.existsById(dto.getUserName()))
        {
            message="Update successfully!";
        }
        else{
            message="Create successfully!";
        }
        try {
            as.save(accout);
        }catch (Exception e)
        {
            e.printStackTrace();
            message="fail!";
        }
        model.addAttribute("message",message);
        return new ModelAndView("forward:/admin/account",model);
    }
    @GetMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable(name="username")String username ,ModelMap model)
    {
        Optional<Account> opt=as.findById(username);
        if (!opt.isPresent())
        {
            String message="User not exists!";
            model.addAttribute("message",message);
            return new ModelAndView("forward:/admin/account",model);
        }else{
            Account account=opt.get();
            AccountDto dto=new AccountDto();
            BeanUtils.copyProperties(account,dto);
            model.addAttribute("account",dto);
            model.addAttribute("isedit",true);
            return new ModelAndView("/admin/account/addOrEdit",model);
        }
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(name="id")String username,ModelMap model) {
        System.out.print(username);
        try {
            as.deleteById(username);
            model.addAttribute("message", "deleted account!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "delete fail!");
        }
        return new ModelAndView("forward:/admin/account", model);

    }
}
