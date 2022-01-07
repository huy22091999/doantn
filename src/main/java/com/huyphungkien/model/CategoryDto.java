package com.huyphungkien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.huyphungkien.domain.Category;
import com.huyphungkien.domain.Product;


public class CategoryDto implements Serializable {
	private Long categoryId;
	private String name;
	private CategoryDto parent;
	private List<CategoryDto> categorys;
	public CategoryDto(){}
	public CategoryDto(Category c,Boolean b){
		this.categoryId=c.getCategoryId();
		this.name=c.getName();
		if(b)
		{
			if(c.getParent()!=null){
				this.parent=new CategoryDto(c.getParent(),true);
			}
		}
		this.categorys=new ArrayList<>();
		if(c.getCategories()!=null&&c.getCategories().size()>=0){
			for (Category item:c.getCategories()) {
				if(item.getCategoryId()!=c.getCategoryId())
				{
					this.categorys.add(new CategoryDto(item,false));
				}
			}
		}
	}

	public CategoryDto getParent() {
		return parent;
	}

	public void setParent(CategoryDto parent) {
		this.parent = parent;
	}

	public List<CategoryDto> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<CategoryDto> categorys) {
		this.categorys = categorys;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}