package dbservice.converter;

import dbservice.dto.BasketDto;
import dbservice.entity.Basket;

public interface BasketConverter {

    BasketDto convertToDto(Basket basket);

    Basket convertToEntity(BasketDto basketDto);
}
