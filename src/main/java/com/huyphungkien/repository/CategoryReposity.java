package com.huyphungkien.repository;

import java.util.List;

import com.huyphungkien.model.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huyphungkien.domain.Category;
@Repository
public interface CategoryReposity extends JpaRepository<Category, Long> {
    @Query("select new com.huyphungkien.model.CategoryDto(c,true) from Category c ")
    public List<CategoryDto> getAllCategory();

    @Query("select new com.huyphungkien.model.CategoryDto(c,true) from Category c where c.parent=null")
    public List<CategoryDto> getCategory();
}
