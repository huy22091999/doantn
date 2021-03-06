package com.huyphungkien.repository;


import com.huyphungkien.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huyphungkien.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	@Query("select new com.huyphungkien.model.ProductDto(p,true) from Product p")
	public Page<ProductDto> findAllPro(Pageable page);
}
