package dbservice.service;

import dbservice.dto.CartDto;
import dbservice.dto.CustomerDto;
import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Address;
import dbservice.entity.Customer;
import dbservice.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {

    OrderDto getById(long id);

    Set<OrderDto> getByCustomerId(long id);

    Set<OrderDto> getByDate(Date date);

    Map<ProductDto, Integer> getOrdersProducts(OrderDto orderDto);

    void add(OrderDto order);

    void deleteById(long id);

    void update(OrderDto order);

    void confirmOrder(OrderDto order, List<CartDto> cartItems);

}
