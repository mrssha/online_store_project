package store.service;

import store.dto.BaseCartDto;
import store.dto.CartDto;
import store.dto.CookieCartDto;
import store.dto.ProductDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface CartService {

    CartDto getById(long id);

    CartDto getByProductAndCustomer(long customer_id, long product_id);

    List<ProductDto> getProductsInCart(long id_customer);

    public BaseCartDto getBaseCartByCustomersEmail(String email);

    void add(CartDto cartDtoItem);

    void deleteById(long id);

    void update(CartDto cartDtoItem);

    String addToCart(String email, Long id_product);

    String removeFromCart(String email, Long id_product);

    List<ProductDto> checkMissingItems(Map<ProductDto, Integer> cartCookie);

    CookieCartDto getCartProductsCookie(Cookie cookieCart) throws UnsupportedEncodingException;

    void mergeCarts(HttpServletResponse response, String email, Cookie cookieCart)
            throws UnsupportedEncodingException;

    void clearCookieCart(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException;
}
