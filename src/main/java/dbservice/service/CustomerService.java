package dbservice.service;

import dbservice.dto.CustomerDto;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    public CustomerDto getCustomerById(long id);

    public CustomerDto getCustomerByEmail(String email);

    public Set<CustomerDto> getAllCustomers();

    public void addCustomer(CustomerDto customerDto);

    public void updateCustomer(CustomerDto customerDto);

    public void deleteCustomerById(long id);
}
