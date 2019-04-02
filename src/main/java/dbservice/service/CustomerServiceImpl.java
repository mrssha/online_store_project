package dbservice.service;

import dbservice.converter.CustomerConverter;
import dbservice.dao.CustomerDao;
import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CustomerDto getCustomerById(long id) {
        return customerConverter.convertToDto(customerDao.getById(id));
    }

    @Override
    @Transactional
    public CustomerDto getCustomerByEmail(String email) {
        return customerConverter.convertToDto(customerDao.getByEmail(email));
    }

    @Override
    @Transactional
    public Set<CustomerDto> getAllCustomers() {
        Set<Customer> allCustomers = new HashSet<>(customerDao.getAll());
        return customerConverter.convertToDtoSet(allCustomers);
    }

    @Override
    @Transactional
    public String  addCustomer(CustomerDto customerDto) {
        String email = customerDto.getEmail();
        if (getCustomerByEmail(email) != null) {
            return "EMAIL_EXIST";
        }
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customerDto.setRole("ROLE_USER");
        customerDao.add(customerConverter.convertToEntity(customerDto));
        return "CUSTOMER_CREATED";
    }

    @Override
    @Transactional
    public CustomerDto changeInfo(CustomerDto customerBefore, CustomerDto customerAfter){
        customerBefore.setFirstName(customerAfter.getFirstName());
        customerBefore.setSecondName(customerAfter.getSecondName());
        //customerBefore.setEmail(customerAfter.getEmail());
        //customerBefore.setBirthDate(customerAfter.getBirthDate());
        updateCustomer(customerBefore);
        return customerBefore;
    }

    @Override
    @Transactional
    public String changePassword(String  email, String oldPassword, String newPassword){
        CustomerDto customerDto = getCustomerByEmail(email);
        if (passwordEncoder.matches(oldPassword, customerDto.getPassword())){
            customerDto.setPassword(passwordEncoder.encode(newPassword));
            updateCustomer(customerDto);
            return "PASSWORD_CHANGED";
        }
        return "INVALID_OLD_PASSWORD";
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDto customerDto) {
        customerDao.update(customerConverter.convertToEntity(customerDto));
    }

    @Override
    @Transactional
    public void deleteCustomerById(long id) {
        customerDao.deleteById(id);
    }
}
