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
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cartDto = (CartDto) o;
        return quantity == cartDto.quantity &&
                id.equals(cartDto.id) &&
                customer.equals(cartDto.customer) &&
                product.equals(cartDto.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, product, quantity);
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
