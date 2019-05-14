package store.dto;

import java.util.Map;
import java.util.Objects;

public class CookieCartDto {

    private Map<ProductDto, Integer> cookieCart;
    private int amountProducts;
    private double totalPrice;

    public CookieCartDto(Map<ProductDto, Integer> cookieCart,
                         int amountProducts, double totalPrice){
        this.cookieCart = cookieCart;
        this.amountProducts = amountProducts;
        this.totalPrice = totalPrice;
    }

    public Map<ProductDto, Integer> getCookieCart() {
        return cookieCart;
    }

    public int getAmountProducts() {
        return amountProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookieCartDto)) return false;
        CookieCartDto that = (CookieCartDto) o;
        return getAmountProducts() == that.getAmountProducts() &&
                Double.compare(that.getTotalPrice(), getTotalPrice()) == 0 &&
                Objects.equals(getCookieCart(), that.getCookieCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCookieCart(), getAmountProducts(), getTotalPrice());
    }
}
