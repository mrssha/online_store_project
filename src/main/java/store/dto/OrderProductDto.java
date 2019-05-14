package store.dto;



import java.util.Objects;

public class OrderProductDto {

    private Long id;

    private OrderDto order;

    private ProductDto product;

    private int quantity;

    public OrderProductDto(){
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
        if (!(o instanceof OrderProductDto)) return false;
        OrderProductDto orderProductDto = (OrderProductDto) o;
        return getQuantity() == orderProductDto.getQuantity() &&
                Objects.equals(getId(), orderProductDto.getId()) &&
                Objects.equals(getOrder(), orderProductDto.getOrder()) &&
                Objects.equals(getProduct(), orderProductDto.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder(), getProduct(), getQuantity());
    }

    @Override
    public String toString() {
        return "OrderProductDto{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
