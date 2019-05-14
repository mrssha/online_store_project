package store.converter;

import store.dto.CartDto;
import store.entity.Cart;

public interface CartConverter {

    CartDto convertToDto(Cart cartItem);

    Cart convertToEntity(CartDto cartDtoItem);
}
