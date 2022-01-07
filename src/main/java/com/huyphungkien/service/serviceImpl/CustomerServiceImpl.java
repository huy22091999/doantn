package com.huyphungkien.service.serviceImpl;

import com.huyphungkien.domain.Customer;
import com.huyphungkien.model.CustomerDto;
import com.huyphungkien.repository.CustomerRepsitory;
import com.huyphungkien.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepsitory cr;
    @Autowired
    private HttpSession session;
    @Override
    @Query("select new com.dungngocshop.model.CustomerDto(e) from Customer e")
    public List<CustomerDto> getAll() {
        return cr.getAll();
    }

    @Override
    @Query("select count(customerId) from Customer e where e.phone= ?1 and(e.customerId !=?2 or ?2=null)")
    public Boolean checkPhone(String phone, Long id) {
        Long check=cr.checkPhone(phone,id);
        if(check>0)
        {
            return false;
        }
        return true;
    }

    @Override
    @Deprecated
    public Customer getOne(Long aLong) {
        return cr.getOne(aLong);
    }

    @Override
    public CustomerDto saveOrUpDate(CustomerDto dto) {
        if (dto != null) {
            Customer customer = null;
            Long id= (Long) session.getAttribute("customerId");
            if (id != null) {
                customer = cr.getOne(id);
            }
            if (customer == null) {
                customer = new Customer();
            }
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setPassword(dto.getPassword());
            customer.setPhone(dto.getPhone());
            customer.setRegisteredDate(dto.getRegisteredDate());
            customer = cr.save(customer);
            if (customer != null) {
                return new CustomerDto(customer,true);
            }
        }
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return cr.existsById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        cr.deleteById(aLong);
    }

    @Override
    public Long login(CustomerDto dto)
    {
        if(cr.login(dto.getPhone(),dto.getPassword())!=null){
            return cr.login(dto.getPhone(),dto.getPassword());
        }
        return null;
    }

    @Override
    public CustomerDto getCustomer() {
        Long id= (Long) session.getAttribute("customerId");
        if(id!=null){
            return cr.getCustomer(id);
        }
        return null;
    }


}
