package dbservice.DAO;

import dbservice.entity.Address;
import dbservice.entity.Customer;
import dbservice.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    Customer getById(long id) throws SQLException;

    Customer getByEmail(String email) throws SQLException;

    List<Customer> getAll();

    //List<Order> getOrders(Customer customer);

    //List<Address> getAddresses(Customer customer);

    void add(Customer customer) throws SQLException;

    void deleteById(long id) throws SQLException;

    void update(Customer customer) throws SQLException;
}


