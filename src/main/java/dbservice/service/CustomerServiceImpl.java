package dbservice.service;

import dbservice.converter.CustomerConverter;
import dbservice.dao.CustomerDao;
import dbservice.dto.ChangeInfoDto;
import dbservice.dto.ChangePasswordDto;
import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import dbservice.result.StatusResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
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
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);


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
    public StatusResult  addCustomer(CustomerDto customerDto) {
        String email = customerDto.getEmail();
        if (getCustomerByEmail(email) != null) {
            logger.info(StatusResult.EMAIL_ALREADY_EXIST.getMessage());
            return StatusResult.EMAIL_ALREADY_EXIST;
        }
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customerDto.setRole("ROLE_USER");
        customerDto.setSumPurchases(0.0);
        customerDao.add(customerConverter.convertToEntity(customerDto));
        logger.info(StatusResult.CUSTOMER_CREATE_SUCCESS.getMessage());
        return StatusResult.CUSTOMER_CREATE_SUCCESS;
    }

    @Override
    @Transactional
    public CustomerDto changeInfo(CustomerDto customer, ChangeInfoDto changeInfo){
        customer.setFirstName(changeInfo.getFirstName());
        customer.setSecondName(changeInfo.getSecondName());
        customer.setBirthDate(changeInfo.getBirthDate());
        updateCustomer(customer);
        return customer;
    }

    @Override
    @Transactional
    public StatusResult changePassword(String  email, ChangePasswordDto changePass){
        CustomerDto customerDto = getCustomerByEmail(email);
        if (passwordEncoder.matches(changePass.getOldPassword(), customerDto.getPassword())){
            customerDto.setPassword(passwordEncoder.encode(changePass.getNewPassword()));
            updateCustomer(customerDto);
            logger.info(StatusResult.PASSWORD_CHANGED_SUCCESS.getMessage());
            return StatusResult.PASSWORD_CHANGED_SUCCESS;
        }
        return StatusResult.INVALID_OLD_PASSWORD;
    }


    @Override
    @Transactional
    public List<CustomerDto> getTopCustomers(){
        List<Customer> topCustomer = customerDao.getTopCustomers();
        List<CustomerDto> topCustomerDto = new ArrayList<>();
        for (Customer customer: topCustomer){
            topCustomerDto.add(customerConverter.convertToDto(customer));
        }
        return topCustomerDto;
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
