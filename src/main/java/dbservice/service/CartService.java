package dbservice.service;

import dbservice.dto.CartDto;
import dbservice.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface CartService {

    CartDto getById(long id);

    List<ProductDto> getProductsInCart(long id_customer);

    List<CartDto> getCartItemsByCustomersEmail(String email);

    void add(CartDto cartDtoItem);

    void deleteById(long id);

    void update(CartDto cartDtoItem);

    String addRemoveCartItem(String email, Long id_product);

    Map<String, String> addToCart(String email, Long id_product);

    Map<String, String> removeFromCart(String email, Long id_product);

    List<ProductDto> checkMissingItems(List<CartDto> cartItems);
}
