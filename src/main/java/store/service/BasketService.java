package store.service;

import store.dto.BasketDto;
import store.dto.OrderDto;
import store.dto.ProductDto;

import java.util.List;

public interface BasketService {

    BasketDto getById(long id);

    List<ProductDto> getProductsInOrder(long id_order);

    List<OrderDto> getOrdersForProduct(long id_product);

    void add(BasketDto basket);

    void deleteById(long id);

    void update(BasketDto basket);
}
