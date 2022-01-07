package com.huyphungkien.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.huyphungkien.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.huyphungkien.domain.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductService {

    Page<ProductDto> searchByPage(String key,Long id);

    Product getById(Long id);

	Product getOne(Long id);

	void deleteById(Long id) throws IOException;

	Optional<Product> findById(Long id);

	Page<Product> findAll(Pageable page);

	ProductDto save(ProductDto dto);

    @Query("select new com.dungngocshop.model.ProductDto(p) from Product p")
    Page<ProductDto> findAllPro(Pageable page);

	List<ProductDto> getProductHot(int status);
}
