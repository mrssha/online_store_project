package dbservice.converter;

import dbservice.dto.OrderDto;
import dbservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Component("orderConverter")
public class OrderConverterImpl implements OrderConverter {

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private AddressConverter addressConverter;

    @Override
    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomer(customerConverter.convertToDto(order.getCustomer()));
        orderDto.setDateOrder(order.getDateOrder());
        orderDto.setCustomerAddress(addressConverter.convertToDto(order.getCustomerAddress()));
        orderDto.setQuantityProducts(order.getQuantityProducts());
        orderDto.setPayment_amount(order.getPayment_amount());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setDeliveryMethod(order.getDeliveryMethod());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        orderDto.setOrderStatus(order.getOrderStatus());
        return orderDto;
    }

    @Override
    public Set<OrderDto> convertToDtoSet(Set<Order> orders) {
        Set<OrderDto> ordersDto = new HashSet<>();
        for (Order order: orders){
            ordersDto.add(convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    public Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(customerConverter.convertToEntity(orderDto.getCustomer()));
        order.setDateOrder(orderDto.getDateOrder());
        order.setCustomerAddress(addressConverter.convertToEntity(orderDto.getCustomerAddress()));
        order.setQuantityProducts(orderDto.getQuantityProducts());
        order.setPayment_amount(orderDto.getPayment_amount());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setDeliveryMethod(orderDto.getDeliveryMethod());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setOrderStatus(orderDto.getOrderStatus());
        return order;
    }

    @Override
    public Set<Order> convertToEntitySet(Set<OrderDto> ordersDto) {
        Set<Order> orders = new HashSet<>();
        for (OrderDto orderDto: ordersDto){
            orders.add(convertToEntity(orderDto));
        }
        return orders;
    }
}
