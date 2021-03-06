package store.dao;

import store.entity.*;

import java.util.List;

public interface CartDao {

    Cart getById(long id);

    Cart getByProductAndCustomer(long id_customer, long id_product);

    List<Product> getProductsInCart(long id_customer);

    List<Cart> getCartItemsForCustomer(long id_customer);

    List<Cart> getCartItemsForProduct(long id_product);

    void add(Cart cartItem);

    void deleteById(long id);

    void update(Cart cartItem);
}
