package dbservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "basket")
public class Basket implements Serializable {

    @EmbeddedId
    private BasketId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public Basket(){

    }


    public Basket(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new BasketId(order.getId(), product.getId());
    }


    //@EmbeddedId
    public BasketId getId() {
        return id;
    }

    public void setId(BasketId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

        Basket basket = (Basket) obj;
        return Objects.equals(this.getOrder(), basket.getOrder()) &&
                Objects.equals(this.getProduct(), basket.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOrder(), this.getProduct());
    }


}
