package dbservice.dto;


import dbservice.entity.BasketId;


public class BasketDto {

    private BasketId id;

    private OrderDto order;

    private ProductDto product;

    private int quantity;

    public BasketDto(){
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


}
