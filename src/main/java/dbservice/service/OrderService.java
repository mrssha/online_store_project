package dbservice.service;

import dbservice.dto.*;
import dbservice.entity.Address;
import dbservice.entity.Customer;
import dbservice.entity.Order;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {

    OrderDto getById(long id);

    List<OrderDto> getByCustomerId(long id);

    List<OrderDto> getAllOrders();

    Set<OrderDto> getByDate(Date date);

    Map<ProductDto, Integer> getOrdersProducts(OrderDto orderDto);

    void add(OrderDto order);

    void deleteById(long id);

    void update(OrderDto order);

    void updateStatus(String orderJson);

    String getRevenueForPeriod(String periodJson);

    void confirmOrder(CustomerDto customerDto, OrderDto order, BaseCartDto baseCartDto);

    //Pair<Integer, Double> getCartParameters(List<CartDto> cartItems);

    Order createNewOrder(OrderDto orderDto, BaseCartDto baseCartDto);

}
