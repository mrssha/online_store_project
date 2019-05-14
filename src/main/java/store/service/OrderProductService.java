package store.service;

import store.dto.OrderProductDto;
import store.dto.OrderDto;
import store.dto.ProductDto;

import java.util.List;

public interface OrderProductService {

    OrderProductDto getById(long id);

    List<ProductDto> getProductsInOrder(long id_order);

    List<OrderDto> getOrdersForProduct(long id_product);

    void add(OrderProductDto basket);

    void deleteById(long id);

    void update(OrderProductDto basket);
}
