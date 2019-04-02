package dbservice.converter;

import dbservice.dto.CartDto;
import dbservice.entity.Cart;

public interface CartConverter {

    CartDto convertToDto(Cart cartItem);

    Cart convertToEntity(CartDto cartDtoItem);
}
