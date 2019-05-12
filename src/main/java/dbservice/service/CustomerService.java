package dbservice.service;

import dbservice.dto.ChangeInfoDto;
import dbservice.dto.ChangePasswordDto;
import dbservice.dto.CustomerDto;
import dbservice.result.StatusResult;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    CustomerDto getCustomerById(long id);

    CustomerDto getCustomerByEmail(String email);

    Set<CustomerDto> getAllCustomers();

    StatusResult addCustomer(CustomerDto customerDto);

    CustomerDto changeInfo(CustomerDto customer, ChangeInfoDto changeInfo);

    StatusResult changePassword(String  email, ChangePasswordDto changePass);

    List<CustomerDto> getTopCustomers();

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(long id);
}
