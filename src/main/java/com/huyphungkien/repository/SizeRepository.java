package com.huyphungkien.repository;

import com.huyphungkien.domain.TypePro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<TypePro,Long> {

}
