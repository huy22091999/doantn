 package com.huyphungkien.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.huyphungkien.domain.Image;
import com.huyphungkien.model.ImageDto;
import com.huyphungkien.model.TypeProDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.huyphungkien.domain.Product;
import com.huyphungkien.model.CategoryDto;
import com.huyphungkien.model.ProductDto;
import com.huyphungkien.service.CategoryService;
import com.huyphungkien.service.ProductService;
import com.huyphungkien.service.StorageService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired
	StorageService ss;
	@Autowired
	ProductService ps;
	@Autowired
	CategoryService cs;
	
	@ModelAttribute("categorys")
	public List<CategoryDto> getCategory(){
		return cs.getAllCategory();
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		ProductDto product=new ProductDto();
		List<TypeProDto> sizeDtos=new ArrayList<TypeProDto>();
		for(int i=0;i<6;i++){
			sizeDtos.add(new TypeProDto());
		}
		MultipartFile[] image = new MultipartFile[10];
		product.setFiles(image);
		product.setSizes(sizeDtos);
		model.addAttribute("product", product);
		return "/admin/product/add";
	}
	@GetMapping("/edit/{id}")
	public ModelAndView editCategory (@PathVariable(name="id")Long id,ModelMap model) {
		Optional<Product> op=ps.findById(id);
		if(op.isPresent()) {
			ProductDto product=new ProductDto(op.get(),true);
			int n=5;
			if(product.getSizes()!=null){
				if(product.getSizes().size()<5);
					n=n-product.getSizes().size();
				for (int i=0;i<n;i++){
					product.getSizes().add(new TypeProDto());
				}
			}
			else{
				List<TypeProDto> sizeDtos=new ArrayList<TypeProDto>();
				for (int i=0;i<n;i++){
					sizeDtos.add(new TypeProDto());
				}
				product.setSizes(sizeDtos);
			}
			MultipartFile[] image = new MultipartFile[10];
			product.setFiles(image);
			model.addAttribute("product",product);
			model.addAttribute("isedit",true);
			return new ModelAndView("/admin/product/add",model);
			
		}
		else {
			model.addAttribute("message", "category not exists!");
			return new ModelAndView("forward:/admin/product");
		}
	}
	@PostMapping("/save")
	public ModelAndView saveOrUpdate(ModelMap model,ProductDto product)
	{
		ps.save(product);
		model.addAttribute("message","created product!");
		return new ModelAndView("forward:/admin/product/",model);
	}
	@RequestMapping("")
	public String getList(@RequestParam("index")Optional<Integer> index,
			@RequestParam("size")Optional<Integer> size,Model model) {
		int pIndex=index.orElse(1);
		int pSize=size.orElse(5);
		
		Pageable page=PageRequest.of(pIndex-1, pSize,Sort.by("name"));
		Page<ProductDto> p=ps.findAllPro(page);
		
		int total=p.getTotalPages();
		
		if(total>0) {
			int start=Math.max(1, pIndex-2);
			int end=Math.min(pIndex+2, total);
			List<Integer> listpage=IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("numpage", listpage);
		}
		model.addAttribute("list", p);
		return"/admin/product/list";
	}
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(name="id")Long id,ModelMap model)
	{
		try {
			ps.deleteById(id);

			model.addAttribute("message", "deleted product!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message", "delete fail!");
		}
		return new ModelAndView("redirect:/admin/product/",model);
	}
}
