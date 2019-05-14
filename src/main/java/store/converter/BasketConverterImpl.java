package store.converter;

import store.dto.BasketDto;
import store.entity.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("basketConverter")
public class BasketConverterImpl implements BasketConverter {

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public BasketDto convertToDto(Basket basket) {
        if (basket == null){
            return null;
        }
        BasketDto basketDto = new BasketDto();
        basketDto.setId(basket.getId());
        basketDto.setOrder(orderConverter.convertToDto(basket.getOrder()));
        basketDto.setProduct(productConverter.convertToDto(basket.getProduct()));
        basketDto.setQuantity(basket.getQuantity());
        return basketDto;
    }

    @Override
    public Basket convertToEntity(BasketDto basketDto) {
        if (basketDto == null){
            return null;
        }
        Basket basket = new Basket();
        basket.setId(basketDto.getId());
        basket.setOrder(orderConverter.convertToEntity(basketDto.getOrder()));
        basket.setProduct(productConverter.convertToEntity(basketDto.getProduct()));
        basket.setQuantity(basketDto.getQuantity());
        return basket;
    }
}
