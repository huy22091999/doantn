package com.huyphungkien.repository;

import com.huyphungkien.domain.Customer;
import com.huyphungkien.model.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepsitory extends JpaRepository<Customer,Long> {
    @Query("select new com.huyphungkien.model.CustomerDto(e,true) from Customer e")
    public List<CustomerDto> getAll();
    @Query("select count(customerId) from Customer e where e.phone= ?1 and(e.customerId !=?2 or ?2=null)")
    public Long checkPhone(String phone,Long id);

    @Query("select e.customerId from Customer e where e.phone= ?1 and e.password=?2")
    public Long login(String phone,String pass);
    @Query("select new com.huyphungkien.model.CustomerDto(e,true) from Customer e where e.id=?1 ")
    public CustomerDto getCustomer(Long id);
}
