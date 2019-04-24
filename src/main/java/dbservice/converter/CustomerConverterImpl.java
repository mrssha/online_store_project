package dbservice.converter;

import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("customerConverter")
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public CustomerDto convertToDto(Customer customer) {
        if (customer == null){
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setSecondName(customer.getSecondName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setBirthDate(customer.getBirthDate());
        customerDto.setSumPurchases(customer.getSumPurchases());
        customerDto.setRole(customer.getRole());
        //customerDto.setAddresses(addressConverter.convertToDtoList(customer.getAddresses()));
        return customerDto;
    }

    @Override
    public Set<CustomerDto> convertToDtoSet(Set<Customer> customers) {
        Set<CustomerDto> customersDto = new HashSet<>();
        for (Customer customer: customers){
            customersDto.add(convertToDto(customer));
        }
        return customersDto;
    }


    @Override
    public Customer convertToEntity(CustomerDto customerDto) {
        if (customerDto == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setSecondName(customerDto.getSecondName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setSumPurchases(customerDto.getSumPurchases());
        customer.setRole(customerDto.getRole());
        //customer.setAddresses(addressConverter.convertToEntityList(customerDto.getAddresses()));
        return customer;
    }

    @Override
    public Set<Customer> convertToEntitySet(Set<CustomerDto> customersDto) {
        Set<Customer> customers = new HashSet<>();
        for (CustomerDto customerDto: customersDto){
            customers.add(convertToEntity(customerDto));
        }
        return customers;
    }
}
