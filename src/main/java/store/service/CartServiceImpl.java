package store.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import store.converter.CartConverter;
import store.converter.ProductConverter;
import store.dao.CartDao;
import store.dao.CustomerDao;
import store.dao.ProductDao;
import store.dto.BaseCartDto;
import store.dto.CartDto;
import store.dto.CookieCartDto;
import store.dto.ProductDto;
import store.entity.Cart;
import store.entity.Customer;
import store.entity.Product;
import store.utils.LogMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;
import store.utils.Message;
import store.utils.StatusResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {

    private CartConverter cartConverter;
    private ProductConverter productConverter;
    private CartDao cartDao;
    private ProductDao productDao;
    private ProductService productService;
    private CustomerDao customerDao;

    private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

    @Override
    @Transactional
    public CartDto getById(long id){
        return cartConverter.convertToDto(cartDao.getById(id));
    }

    @Override
    @Transactional
    public CartDto getByProductAndCustomer(long customer_id, long product_id){
        return cartConverter.convertToDto(
                cartDao.getByProductAndCustomer(customer_id, product_id));
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsInCart(long id_customer){
        List<Product> products = cartDao.getProductsInCart(id_customer);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product:  products){
            productsDto.add(productConverter.convertToDto(product));
        }
        return productsDto;
    }

    @Override
    @Transactional
    public void add(CartDto cartDtoItem){
        cartDao.add(cartConverter.convertToEntity(cartDtoItem));
    }


    @Override
    @Transactional
    public String  addToCart(String email, Long id_product){
        Gson gson = new Gson();
        Product product = productDao.getById(id_product);
        if (product.getQuantity() == 0){
            return gson.toJson(new Message(StatusResult.NO_PRODUCTS));
        }
        Customer customer = customerDao.getByEmail(email);
        Cart cartItem = cartDao.getByProductAndCustomer(customer.getId(), id_product);
        if (cartItem!= null){
            int quantityItem = cartItem.getQuantity();
            if (product.getQuantity() <= quantityItem){
                return gson.toJson(new Message(StatusResult.NO_PRODUCTS));
            }
            cartItem.setQuantity(quantityItem + 1);
            cartDao.update(cartItem);
            return gson.toJson(new Message(StatusResult.PRODUCT_ADDED));
        }
        Cart newCartItem = new Cart();
        newCartItem.setCustomer(customer);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(1);
        cartDao.add(newCartItem);
        return gson.toJson(new Message(StatusResult.PRODUCT_ADDED));
    }


    @Override
    @Transactional
    public String removeFromCart(String email, Long id_product){
        Gson gson = new Gson();
        Customer customer = customerDao.getByEmail(email);
        Cart cartItem = cartDao.getByProductAndCustomer(customer.getId(), id_product);
        if (cartItem == null || cartItem.getQuantity() == 1){
            return gson.toJson(new Message(StatusResult.PRODUCT_FAIL_DELETE));
        }
        int oldQuantity = cartItem.getQuantity();
        cartItem.setQuantity(oldQuantity - 1);
        cartDao.update(cartItem);
        return gson.toJson(new Message(StatusResult.PRODUCT_REMOVED));
    }


    @Override
    @Transactional
    public BaseCartDto getBaseCartByCustomersEmail(String email){
        Customer customer = customerDao.getByEmail(email);
        List<Cart> cartItems = cartDao.getCartItemsForCustomer(customer.getId());
        List<CartDto> cartDtoItems = new ArrayList<>();
        int amount = 0;
        int totalSum = 0;
        for (Cart cartItem: cartItems){
            amount += cartItem.getQuantity();
            totalSum += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            cartDtoItems.add(cartConverter.convertToDto(cartItem));
        }
        BaseCartDto baseCartDto = new BaseCartDto();
        baseCartDto.setCartItems(cartDtoItems);
        baseCartDto.setAmountProducts(amount);
        baseCartDto.setTotalSum(totalSum);
        return baseCartDto;
    }


    @Override
    @Transactional
    public void deleteById(long id){
        cartDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(CartDto cartDtoItem){
        cartDao.update(cartConverter.convertToEntity(cartDtoItem));
    }



    @Override
    @Transactional
    public List<ProductDto> checkMissingItems(Map<ProductDto, Integer> mapCookie){
        List<ProductDto> missingProducts = new ArrayList<>();
        for (Map.Entry<ProductDto, Integer> pair : mapCookie.entrySet()) {
            ProductDto productInCart = pair.getKey();
            int quantityInCart = pair.getValue();
            if (quantityInCart > productDao.getById(productInCart.getId()).getQuantity()){
                missingProducts.add(productInCart);
            }
        }
        return missingProducts;
    }


    @Override
    @Transactional
    public CookieCartDto getCartProductsCookie(Cookie cookieCart)
            throws UnsupportedEncodingException {
        Map<ProductDto, Integer> productCartMap = new HashMap<>();
        Map<Long, Integer> mapCookie = getCookieCart(cookieCart);
        int amount = 0;
        double total = 0;
        if (mapCookie!=null) {
            for (Map.Entry<Long, Integer> pair : mapCookie.entrySet()) {
                ProductDto productDto = productService.getById(pair.getKey());
                if (productDto != null) {
                    productCartMap.put(productDto, pair.getValue());
                    amount += pair.getValue();
                    total += productDto.getPrice() * pair.getValue();
                }
            }
        }
        return new CookieCartDto(productCartMap, amount, total);
    }


    private Map<Long, Integer> getCookieCart(Cookie cookieCart) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        if (cookieCart == null){
            return new HashMap<>();
        }
        Type typeToken = new TypeToken<HashMap<Long, Integer>>(){}.getType();
        HashMap<Long, Integer> mapCookie = gson.fromJson(URLDecoder.decode(cookieCart.getValue(),"UTF-8"), typeToken);
        if (mapCookie == null){
            return new HashMap<>();
        }
        return mapCookie;
    }


    @Override
    @Transactional
    public void mergeCarts(HttpServletResponse response, String email, Cookie cookieCart)
            throws UnsupportedEncodingException{
        BaseCartDto baseCartDto = getBaseCartByCustomersEmail(email);
        List<CartDto> cartDbItems = baseCartDto.getCartItems();
        Map<Long, Integer> mapCookieCart = getCookieCart(cookieCart);
        for (CartDto cartItemDb: cartDbItems){
            ProductDto productDto = cartItemDb.getProduct();
            if (!mapCookieCart.containsKey(productDto.getId())){
                mapCookieCart.put(productDto.getId(), cartItemDb.getQuantity());
            }
        }
        Customer customer = customerDao.getByEmail(email);
        for (Map.Entry<Long, Integer> cookieCartItem: mapCookieCart.entrySet()){
            Product product = productDao.getById(cookieCartItem.getKey());
            int quantityProductCookie = cookieCartItem.getValue();
            Cart dbCartItem = cartDao.getByProductAndCustomer(customer.getId(), product.getId());
            if (dbCartItem!= null){
                dbCartItem.setQuantity(quantityProductCookie);
                cartDao.update(dbCartItem);
            } else {
                Cart newCartItem = new Cart();
                newCartItem.setCustomer(customer);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantityProductCookie);
                cartDao.add(newCartItem);
            }
        }

        Gson gson = new Gson();
        cookieCart.setValue(URLEncoder.encode(gson.toJson(mapCookieCart), "UTF-8"));
        cookieCart.setMaxAge(24 * 60 * 60 * 1000);
        cookieCart.setPath("/");
        response.addCookie(cookieCart);
        logger.info(LogMessage.MERGE_CARTS);
    }


    @Override
    public void clearCookieCart(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException{
        Cookie cookieCart = WebUtils.getCookie(request, "productCart");
        Gson gson = new Gson();
        if (cookieCart!=null){
            HashMap<Long, Integer> map = new HashMap<>();
            cookieCart.setValue(URLEncoder.encode(gson.toJson(map), "UTF-8"));
            cookieCart.setMaxAge(24 * 60 * 60 * 1000);
            cookieCart.setPath("/");
            response.addCookie(cookieCart);
        }
        logger.info(LogMessage.CLEAR_COOKIE);
    }

    @Autowired
    public void setCartConverter(CartConverter cartConverter) {
        this.cartConverter = cartConverter;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}


