package store.dao;

import store.entity.Address;
import store.entity.AddressType;
import store.entity.Customer;

import java.util.List;

public interface AddressDao {

    Address getById(long id);

    List<Address> getByCustomerId(long id);

    List<Address> getByCustomer(Customer customer);

    List<Address> getByAddressType(AddressType type);

    List<Address> getAll();

    void add(Address address);

    void deleteById(long id);

    void update(Address address);

}
