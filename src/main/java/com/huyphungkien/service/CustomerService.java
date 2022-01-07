package com.huyphungkien.service;

import com.huyphungkien.domain.Customer;
import com.huyphungkien.model.CustomerDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerService {
    @Query("select new com.dungngocshop.model.CustomerDto(e) from Customer e")
    List<CustomerDto> getAll();

    @Query("select count(customerId) from Customer e where e.phone= ?1 and(e.customerId !=?2 or ?2=null")
    Boolean checkPhone(String phone, Long id);

    @Deprecated
    Customer getOne(Long aLong);

    CustomerDto saveOrUpDate( CustomerDto dto);

    boolean existsById(Long aLong);

    void deleteById(Long aLong);

    Long login(CustomerDto dto);

    CustomerDto getCustomer();
}
