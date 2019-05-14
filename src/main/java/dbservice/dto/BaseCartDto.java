package dbservice.dto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseCartDto)) return false;
        BaseCartDto that = (BaseCartDto) o;
        return Objects.equals(getCartItems(), that.getCartItems()) &&
                Objects.equals(getAmountProducts(), that.getAmountProducts()) &&
                Objects.equals(getTotalSum(), that.getTotalSum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartItems(), getAmountProducts(), getTotalSum());
    }
}
