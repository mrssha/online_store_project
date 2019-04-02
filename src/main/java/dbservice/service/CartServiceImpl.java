package dbservice.service;

import dbservice.converter.CartConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.CartDao;
import dbservice.dao.CustomerDao;
import dbservice.dao.ProductDao;
import dbservice.dto.CartDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Cart;
import dbservice.entity.Customer;
import dbservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartConverter cartConverter;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CustomerDao customerDao;


    @Override
    @Transactional
    public CartDto getById(long id){
        return cartConverter.convertToDto(cartDao.getById(id));
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
    public String addRemoveCartItem(String email, Long id_product){
        Customer customer = customerDao.getByEmail(email);
        Cart cartItem = cartDao.getByProductAndCustomer(customer.getId(), id_product);
        if (cartItem!= null){
            cartDao.deleteById(cartItem.getId());
            return "REMOVED_FROM_CART";
        }
        Cart newCartItem = new Cart();
        newCartItem.setCustomer(customer);
        newCartItem.setProduct(productDao.getById(id_product));
        newCartItem.setQuantity(1);
        cartDao.add(newCartItem);
        return "ADDED_TO_CART";
    }


    @Override
    @Transactional
    public Map<String, String> addToCart(String email, Long id_product){
        Map<String, String> response = new HashMap<>();
        Product product = productDao.getById(id_product);
        if (product.getQuantity() == 0){
            response.put("message", "NO_PRODUCT");
            return response;
        }

        Customer customer = customerDao.getByEmail(email);
        Cart cartItem = cartDao.getByProductAndCustomer(customer.getId(), id_product);
        if (cartItem!= null){
            int quantityItem = cartItem.getQuantity();
            if (product.getQuantity() <= quantityItem){
                response.put("message", "NO_PRODUCT");
                return response;
            }
            cartItem.setQuantity(quantityItem + 1);
            cartDao.update(cartItem);
            response.put("message", "ADDED_TO_CART");
            response.put("quantity", String.valueOf(quantityItem + 1));
            return response;
        }
        Cart newCartItem = new Cart();
        newCartItem.setCustomer(customer);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(1);
        cartDao.add(newCartItem);
        response.put("message", "ADDED_TO_CART");
        response.put("quantity", String.valueOf(1));
        return response;
    }


    @Override
    @Transactional
    public Map<String, String> removeFromCart(String email, Long id_product){
        Map<String, String> response = new HashMap<>();
        Customer customer = customerDao.getByEmail(email);
        Cart cartItem = cartDao.getByProductAndCustomer(customer.getId(), id_product);
        if (cartItem == null || cartItem.getQuantity() == 1){
            response.put("message", "DONT_REMOVED");
            return response;
        }
        int oldQuantity = cartItem.getQuantity();
        cartItem.setQuantity(oldQuantity - 1);
        cartDao.update(cartItem);
        response.put("message", "REMOVED_FROM_CART");
        response.put("quantity", String.valueOf(oldQuantity - 1));
        return response;
    }


    @Override
    @Transactional
    public List<CartDto> getCartItemsByCustomersEmail(String email){
        Customer customer = customerDao.getByEmail(email);
        List<Cart> cartItems = cartDao.getCartItemsForCustomer(customer.getId());
        List<CartDto> cartDtoItems = new ArrayList<>();
        for (Cart cartItem: cartItems){
            cartDtoItems.add(cartConverter.convertToDto(cartItem));
        }
        return cartDtoItems;
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
    public List<ProductDto> checkMissingItems(List<CartDto> cartItems){
        List<ProductDto> missingProducts = new ArrayList<>();

        for (CartDto cartItem: cartItems){
            ProductDto productInCart = cartItem.getProduct();
            int quantityInCart = cartItem.getQuantity();
            if (quantityInCart > productDao.getById(productInCart.getId()).getQuantity()){
                missingProducts.add(productInCart);
            }
        }
        return missingProducts;
    }
}


