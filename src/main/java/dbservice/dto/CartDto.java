package dbservice.dto;


import java.util.Objects;

public class CartDto {

    private Long id;
    private CustomerDto customer;
    private ProductDto product;
    private int quantity;

    public CartDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDto)) return false;
        CartDto cartDto = (CartDto) o;
        return getQuantity() == cartDto.getQuantity() &&
                Objects.equals(getId(), cartDto.getId()) &&
                Objects.equals(getCustomer(), cartDto.getCustomer()) &&
                Objects.equals(getProduct(), cartDto.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getProduct(), getQuantity());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
