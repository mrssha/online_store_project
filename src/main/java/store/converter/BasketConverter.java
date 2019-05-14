package store.converter;

import store.dto.BasketDto;
import store.entity.Basket;

public interface BasketConverter {

    BasketDto convertToDto(Basket basket);

    Basket convertToEntity(BasketDto basketDto);
}
