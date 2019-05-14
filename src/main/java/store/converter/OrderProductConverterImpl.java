package store.converter;

import store.dto.OrderProductDto;
import store.entity.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("basketConverter")
public class OrderProductConverterImpl implements OrderProductConverter {

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public OrderProductDto convertToDto(OrderProduct orderProduct) {
        if (orderProduct == null){
            return null;
        }
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setId(orderProduct.getId());
        orderProductDto.setOrder(orderConverter.convertToDto(orderProduct.getOrder()));
        orderProductDto.setProduct(productConverter.convertToDto(orderProduct.getProduct()));
        orderProductDto.setQuantity(orderProduct.getQuantity());
        return orderProductDto;
    }

    @Override
    public OrderProduct convertToEntity(OrderProductDto orderProductDto) {
        if (orderProductDto == null){
            return null;
        }
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(orderProductDto.getId());
        orderProduct.setOrder(orderConverter.convertToEntity(orderProductDto.getOrder()));
        orderProduct.setProduct(productConverter.convertToEntity(orderProductDto.getProduct()));
        orderProduct.setQuantity(orderProductDto.getQuantity());
        return orderProduct;
    }
}
