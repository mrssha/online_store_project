package dbservice.service;

import dbservice.dto.CartDto;
import dbservice.dto.CookieCartDto;
import dbservice.dto.ProductDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CartService {

    CartDto getById(long id);

    CartDto getByProductAndCustomer(long customer_id, long product_id);

    List<ProductDto> getProductsInCart(long id_customer);

    List<CartDto> getCartItemsByCustomersEmail(String email);

    void add(CartDto cartDtoItem);

    void deleteById(long id);

    void update(CartDto cartDtoItem);

    String addRemoveCartItem(String email, Long id_product);

    Map<String, String> addToCart(String email, Long id_product);

    Map<String, String> removeFromCart(String email, Long id_product);

    List<ProductDto> checkMissingItems(List<CartDto> cartItems);

    List<ProductDto> checkMissingItems(Map<ProductDto, Integer> cartCookie);

    CookieCartDto getCartProductsCookie(Cookie cookieCart) throws UnsupportedEncodingException;

    Map<Long, Integer> getCookieCart(Cookie cookieCart) throws UnsupportedEncodingException;

    void mergeCarts(HttpServletResponse response, String email, Cookie cookieCart)
            throws UnsupportedEncodingException;

    void mergeToCookieCart(HttpServletResponse response, String email, Cookie cookieCart)
            throws UnsupportedEncodingException;

    void mergeToDBCart(String email, Cookie cookieCart) throws UnsupportedEncodingException;

    void clearCookieCart(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException;
}
