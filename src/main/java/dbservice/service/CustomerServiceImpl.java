package dbservice.service;

import dbservice.converter.CustomerConverter;
import dbservice.dao.CustomerDao;
import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public CustomerDto getCustomerById(long id) {
        return customerConverter.convertToDto(customerDao.getById(id));
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        return customerConverter.convertToDto(customerDao.getByEmail(email));
    }

    @Override
    public Set<CustomerDto> getAllCustomers() {
        Set<Customer> allCustomers = new HashSet<>(customerDao.getAll());
        return customerConverter.convertToDtoSet(allCustomers);
    }

    @Override
    @Transactional
    public void addCustomer(CustomerDto customerDto) {
        customerDao.add(customerConverter.convertToEntity(customerDto));
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
