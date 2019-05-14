package store.converter;

import store.dto.CartDto;
import store.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cartConverter")
public class CartConverterImpl implements CartConverter {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public CartDto convertToDto(Cart cartItem) {
        if (cartItem == null){
            return null;
        }
        CartDto cartDtoItem = new CartDto();
        cartDtoItem.setId(cartItem.getId());
        cartDtoItem.setCustomer(customerConverter.convertToDto(cartItem.getCustomer()));
        cartDtoItem.setProduct(productConverter.convertToDto(cartItem.getProduct()));
        cartDtoItem.setQuantity(cartItem.getQuantity());
        return cartDtoItem;
    }

    @Override
    public Cart convertToEntity(CartDto cartDtoItem) {
        if (cartDtoItem == null){
            return null;
        }
        Cart cartItem = new Cart();
        cartItem.setId(cartDtoItem.getId());
        cartItem.setCustomer(customerConverter.convertToEntity(cartDtoItem.getCustomer()));
        cartItem.setProduct(productConverter.convertToEntity(cartDtoItem.getProduct()));
        cartItem.setQuantity(cartDtoItem.getQuantity());
        return cartItem;
    }
}
