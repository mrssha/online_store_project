package dbservice.dto;

import java.util.Map;

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
}
