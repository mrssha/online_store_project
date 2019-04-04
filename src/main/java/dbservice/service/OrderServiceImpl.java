package dbservice.service;

import dbservice.converter.OrderConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.BasketDao;
import dbservice.dao.CartDao;
import dbservice.dao.OrderDao;
import dbservice.dao.ProductDao;
import dbservice.dto.*;
import dbservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    BasketDao basketDao;

    @Autowired
    CartDao cartDao;

    public Map<ProductDto, Integer> getOrdersProducts(OrderDto orderDto){
        return null;
    }

    @Override
    @Transactional
    public OrderDto getById(long id) {
        return orderConverter.convertToDto(orderDao.getById(id));
    }


    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public List<OrderDto> getByCustomerId(long id) {
        List<Order> orders = orderDao.getByCustomerId(id);
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
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



    @Override
    @Transactional
    public void confirmOrder(OrderDto orderDto, List<CartDto> cartItems){
        Order newOrder = createNewOrder(orderDto, cartItems);
        orderDao.add(newOrder);

        for (CartDto cartItem: cartItems){
            Basket orderProduct = new Basket();
            orderProduct.setOrder(newOrder);
            orderProduct.setProduct(productConverter.convertToEntity(cartItem.getProduct()));
            orderProduct.setQuantity(cartItem.getQuantity());
            basketDao.add(orderProduct);

            Product changedProduct = productDao.getById(cartItem.getProduct().getId());
            int oldQuantity = changedProduct.getQuantity();
            changedProduct.setQuantity(oldQuantity - cartItem.getQuantity());
            productDao.update(changedProduct);

            cartDao.deleteById(cartItem.getId());
        }


    }


    private Order createNewOrder(OrderDto orderDto, List<CartDto> cartItems){
        Order order = orderConverter.convertToEntity(orderDto);
        Date date = new Date();
        order.setDateOrder(date);
        order.setPaymentStatus(PaymentStatus.WAITING);
        order.setOrderStatus(OrderStatus.WAIT_PAYMENT);
        int amount = 0;
        double totalPrice = 0;
        for(CartDto cart: cartItems){
            amount += cart.getQuantity();
            totalPrice += cart.getProduct().getPrice() * cart.getQuantity();
        }
        order.setQuantityProducts(amount);
        order.setPayment_amount(totalPrice);
        return order;
    }

}



