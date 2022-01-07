package com.huyphungkien.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.huyphungkien.model.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyphungkien.domain.Category;
import com.huyphungkien.repository.CategoryReposity;
import com.huyphungkien.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryReposity crepo;

	@Override
	public <S extends Category> S save(S entity) {
		return crepo.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return crepo.findAll();
	}
	@Override
	public List<CategoryDto> findAllNotParent() {
		return crepo.getCategory();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return crepo.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		crepo.deleteById(id);
	}

	@Override
	public Category getOne(Long id) {
		return crepo.getOne(id);
	}

	@Override
	public Category getById(Long id) {
		return crepo.getById(id);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		return crepo.getAllCategory();
	}

	@Override
	public CategoryDto getCategory(Long id){
		Category category=crepo.getOne(id);
		if(category!=null){
			return new CategoryDto(category,true);
		}
		return null;
	}


}
