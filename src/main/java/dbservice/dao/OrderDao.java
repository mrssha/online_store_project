package dbservice.dao;

import dbservice.entity.Customer;
import dbservice.entity.Order;
import dbservice.entity.Product;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    Order getById(long id);

    List<Order> getByCustomer(Customer customer);

    List<Order> getByDate(Date date);

    int getProductQuantity(Order order, Product product);

    //List<Product> getProducts(Order order);

    //Address getAddress(Order order);

    void add(Order order);

    void deleteById(long id);

    void update(Order order);

}
