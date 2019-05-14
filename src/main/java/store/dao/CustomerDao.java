package store.dao;

import store.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getById(long id);

    Customer getByEmail(String email);

    List<Customer> getAll();

    List<Customer> getTopCustomers();

    //List<Order> getOrders(Customer customer);

    //List<Address> getAddresses(Customer customer);

    void add(Customer customer);

    void deleteById(long id);

    void update(Customer customer);
}


