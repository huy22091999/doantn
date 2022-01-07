package com.huyphungkien.service;

import java.util.List;
import java.util.Optional;

import com.huyphungkien.model.CategoryDto;

import com.huyphungkien.domain.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryService {

	Category getById(Long id);

	Category getOne(Long id);

	void deleteById(Long id);

	Optional<Category> findById(Long id);

	List<Category> findAll();

	List<CategoryDto> findAllNotParent();

	<S extends Category> S save(S entity);


    @Query("select new com.dungngocshop.model.CategoryDto(c,false) from Category c")
    List<CategoryDto> getAllCategory();

	CategoryDto getCategory(Long id);
}
