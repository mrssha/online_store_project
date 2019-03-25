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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BasketDto basket = (BasketDto) obj;
        return basket.getId().equals(this.getId()) &&
                Objects.equals(this.getOrder(), basket.getOrder()) &&
                Objects.equals(this.getProduct(), basket.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getOrder(), this.getProduct());
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
