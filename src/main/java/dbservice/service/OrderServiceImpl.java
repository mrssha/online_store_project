package dbservice.service;

import dbservice.converter.OrderConverter;
import dbservice.dao.OrderDao;
import dbservice.dto.CustomerDto;
import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderDao orderDao;

    public Map<ProductDto, Integer> getOrdersProducts(OrderDto orderDto){
        return null;
    }

    @Override
    public OrderDto getById(long id) {
        return orderConverter.convertToDto(orderDao.getById(id));
    }

    @Override
    public Set<OrderDto> getByCustomerId(long id) {
        Set<Order> orders = new HashSet<>(orderDao.getByCustomerId(id));
        return orderConverter.convertToDtoSet(orders);
    }

    @Override
    public Set<OrderDto> getByDate(Date date) {
        Set<Order> orders = new HashSet<>(orderDao.getByDate(date));
        return orderConverter.convertToDtoSet(orders);
    }

    @Override
    @Transactional
    public void add(OrderDto order) {
        orderDao.add(orderConverter.convertToEntity(order));

    }

    @Override
    @Transactional
    public void update(OrderDto order) {
        orderDao.update(orderConverter.convertToEntity(order));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }


}
