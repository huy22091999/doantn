package com.huyphungkien.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huyphungkien.domain.Category;
import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	CategoryService cs;
	@ModelAttribute("categorys")
	public List<CategoryDto> getCategory(){
		return cs.getAllCategory();
	}

	@GetMapping("/add")
	public String addCategory (Model model) {
		model.addAttribute("category",new CategoryDto());
		return "admin/category/add";
	}
	@GetMapping("/edit/{id}")
	public ModelAndView editCategory (@PathVariable(name="id")Long id,ModelMap model) {
		Optional<Category> op=cs.findById(id);
		if(op.isPresent()) {
			Category cate=op.get();
			BeanUtils.copyProperties(op, cate);
			model.addAttribute("category",cate);
			model.addAttribute("isedit",true);
			return new ModelAndView("/admin/category/add",model);
			
		}
		else {
			model.addAttribute("message", "Danh mục không tồn tại!");
			return new ModelAndView("forward:/admin/category/");
		}
	}
	@PostMapping("/saveorupdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("category") CategoryDto cate,BindingResult result ){
		if(result.hasErrors())
		{
			return new ModelAndView("admin/category/add");
		}
		Category ca=new Category();
		model.addAttribute("message", "Thêm danh mục thành công");
		BeanUtils.copyProperties(cate, ca);
		Category parent=new Category();
		parent.setCategoryId(cate.getParent().getCategoryId());
		ca.setParent(parent);
		cs.save(ca);
		return new ModelAndView("forward:/admin/category/",model);
	}
	@RequestMapping("")
	public String list(Model model) {
		List<CategoryDto> list=cs.getAllCategory();
		model.addAttribute("list", list);
		return"admin/category/list";
	}
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(name="id")Long id,ModelMap model)
	{
		System.out.print(id);
		try {
			cs.deleteById(id);
			model.addAttribute("message", "Xóa danh mục thành công !");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message", "Xoá danh mục thất bại!");
		}
		return new ModelAndView("forward:/admin/category/",model);
	}
	
	
	
	

}
