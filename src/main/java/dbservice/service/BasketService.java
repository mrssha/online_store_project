package dbservice.service;

import dbservice.dto.BasketDto;
import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;

import java.util.List;

public interface BasketService {

    BasketDto getById(long id);

    List<ProductDto> getProductsInOrder(long id_order);

    List<OrderDto> getOrdersForProduct(long id_product);

    void add(BasketDto basket);

    void deleteById(long id);

    void update(BasketDto basket);
}
