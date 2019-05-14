package store.converter;

import store.dto.CustomerDto;
import store.entity.Customer;

import java.util.Set;

public interface CustomerConverter {

    CustomerDto convertToDto(Customer customer);

    Customer convertToEntity(CustomerDto customerDto);

    Set<CustomerDto> convertToDtoSet(Set<Customer> customers);

    Set<Customer> convertToEntitySet(Set<CustomerDto> customersDto);
}
