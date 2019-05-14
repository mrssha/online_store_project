package store.dao;

import store.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    Order getById(long id);

    List<Order> getByCustomerId(long id);

    List<Order> getAllOrders();

    List<Order> getByDate(Date date);

    Double getRevenueForPeriod(Integer year, String month);

    Double getRevenueForLastWeek();

    void add(Order order);

    void deleteById(long id);

    void update(Order order);

}
