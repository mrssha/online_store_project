package dbservice.dao;

import dbservice.entity.Basket;
import dbservice.entity.Order;
import dbservice.entity.Product;

import java.util.List;
import java.util.Set;

public interface BasketDao {

    List<Product> getProductsInOrder(Order order);

    List<Basket> getOrdersBasket(Order order);

    void add(Basket basket);

    //void deleteByOrder(Order order);

    //void update(Basket basket);
}
