package store.converter;

import store.dto.OrderDto;
import store.entity.Order;

import java.util.Set;

public interface OrderConverter {

    OrderDto convertToDto(Order product);

    Set<OrderDto> convertToDtoSet(Set<Order> orders);

    Order convertToEntity(OrderDto orderDto);

    Set<Order> convertToEntitySet(Set<OrderDto> ordersDto);

}
