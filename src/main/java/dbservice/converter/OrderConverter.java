package dbservice.converter;

import dbservice.dto.OrderDto;
import dbservice.entity.Order;

import java.util.List;
import java.util.Set;

public interface OrderConverter {

    OrderDto convertToDto(Order product);

    Set<OrderDto> convertToDtoSet(Set<Order> orders);

    Order convertToEntity(OrderDto orderDto);

    Set<Order> convertToEntitySet(Set<OrderDto> ordersDto);

}
