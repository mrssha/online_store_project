package dbservice.dto;



import java.util.Objects;

public class BasketDto {

    private Long id;

    private OrderDto order;

    private ProductDto product;

    private int quantity;

    public BasketDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
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
        if (!(o instanceof BasketDto)) return false;
        BasketDto basketDto = (BasketDto) o;
        return getQuantity() == basketDto.getQuantity() &&
                Objects.equals(getId(), basketDto.getId()) &&
                Objects.equals(getOrder(), basketDto.getOrder()) &&
                Objects.equals(getProduct(), basketDto.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder(), getProduct(), getQuantity());
    }

    @Override
    public String toString() {
        return "BasketDto{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
