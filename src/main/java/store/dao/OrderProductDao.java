package store.dao;

import store.entity.OrderProduct;
import store.entity.Order;
import store.entity.Product;

import java.util.List;

public interface OrderProductDao {

    OrderProduct getById(long id);

    List<Product> getProductsInOrder(long id_order);

    List<Order> getOrdersForProduct(long id_product);

    /*
    List<OrderProduct> getBasketsByProduct(Product product);

    List<OrderProduct> getBasketsByOrder(Order order);
    */

    void add(OrderProduct orderProduct);

    void deleteById(long id);

    void update(OrderProduct orderProduct);
}
