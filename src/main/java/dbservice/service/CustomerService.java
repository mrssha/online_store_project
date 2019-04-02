package dbservice.service;

import dbservice.dto.CustomerDto;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    CustomerDto getCustomerById(long id);

    CustomerDto getCustomerByEmail(String email);

    Set<CustomerDto> getAllCustomers();

    String addCustomer(CustomerDto customerDto);

    CustomerDto changeInfo(CustomerDto customerBefore, CustomerDto customerAfter);

    String changePassword(String  email, String oldPassword, String newPassword);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(long id);
}
