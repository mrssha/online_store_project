package store.dao;

import store.entity.Basket;
import store.entity.Order;
import store.entity.Product;

import java.util.List;

public interface BasketDao {

    Basket getById(long id);

    List<Product> getProductsInOrder(long id_order);

    List<Order> getOrdersForProduct(long id_product);

    /*
    List<Basket> getBasketsByProduct(Product product);

    List<Basket> getBasketsByOrder(Order order);
    */

    void add(Basket basket);

    void deleteById(long id);

    void update(Basket basket);
}
