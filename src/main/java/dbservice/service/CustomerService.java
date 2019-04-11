package dbservice.service;

import dbservice.dto.ChangeInfoDto;
import dbservice.dto.ChangePasswordDto;
import dbservice.dto.CustomerDto;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    CustomerDto getCustomerById(long id);

    CustomerDto getCustomerByEmail(String email);

    Set<CustomerDto> getAllCustomers();

    String addCustomer(CustomerDto customerDto);

    CustomerDto changeInfo(CustomerDto customer, ChangeInfoDto changeInfo);

    String changePassword(String  email, ChangePasswordDto changePass);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(long id);
}
