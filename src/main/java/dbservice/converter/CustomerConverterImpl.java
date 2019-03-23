package dbservice.converter;

import dbservice.dto.CustomerDto;
import dbservice.entity.Customer;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("customerConverter")
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setSecondName(customer.getSecondName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setBirthDate(customer.getBirthDate());
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
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setSecondName(customerDto.getSecondName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setBirthDate(customerDto.getBirthDate());
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