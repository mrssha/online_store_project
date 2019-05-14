package store.converter;

import store.dto.OrderProductDto;
import store.entity.OrderProduct;

public interface OrderProductConverter {

    OrderProductDto convertToDto(OrderProduct orderProduct);

    OrderProduct convertToEntity(OrderProductDto orderProductDto);
}
