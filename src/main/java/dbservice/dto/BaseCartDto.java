package dbservice.dto;

import java.util.List;

public class BaseCartDto {

    private List<CartDto> cartItems;
    private Integer amountProducts;
    private Double totalSum;

    public BaseCartDto(){
    }

    public List<CartDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartDto> cartItems) {
        this.cartItems = cartItems;
    }

    public int getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(int amountProducts) {
        this.amountProducts = amountProducts;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
}
