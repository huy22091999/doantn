package com.huyphungkien.controller.user;

import com.huyphungkien.domain.Product;
import com.huyphungkien.model.CartItemDto;
import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.model.ProductDto;
import com.huyphungkien.service.CartItemService;
import com.huyphungkien.service.CategoryService;
import com.huyphungkien.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    CategoryService cs;
    @Autowired
    ProductService ps;
    @Autowired
    CartItemService cis;
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

    @ModelAttribute("categorys")
    public List<CategoryDto> listCategory() {
        List<CategoryDto> list=cs.findAllNotParent();
        return list;
    }

    @RequestMapping("")
    public String home(Model model) {
        Pageable page = PageRequest.of(0, 10);
        Page<ProductDto> list = ps.findAllPro(page);
        model.addAttribute("products", list);
        return "/site/home";
    }

    @RequestMapping("product/{id}")
    public String detail(@PathVariable(value = "id", required = true) Long id, Model model) {
        Optional<Product> pro = ps.findById(id);
        if (pro.isPresent()) {
            model.addAttribute("product", pro.get());
            Pageable page = PageRequest.of(0, 10);
            Page<ProductDto> list = ps.findAllPro(page);
            model.addAttribute("products", list);
        } else {
            model.addAttribute("message", "Không tìm thấy thông tin sản phẩm");
        }
        String key=pro.get().getName();
        Page<ProductDto> listlq=ps.searchByPage(key,pro.get().getProductId());
        model.addAttribute("listlq",listlq);
        List<ProductDto> listhot=ps.getProductHot(2);
        model.addAttribute("listhot",listhot);
        model.addAttribute("cart", new CartItemDto());
        return "/site/detail-product";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name = "key") String key,Model model){
        Pageable page = PageRequest.of(0, 10);
        Page<ProductDto> list = ps.searchByPage(key,null);
        model.addAttribute("key",key);
        model.addAttribute("listSearch", list);
        return "/site/pro-category";
    }

    @GetMapping("/category/{id}")
    public String getCategory(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("category", cs.getCategory(id));
        return "/site/pro-category";
    }

}
