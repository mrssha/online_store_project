package dbservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Basket implements Serializable {

    @Column(name = "id_order")
    private Long orderId;

    @Column(name = "id_product")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    public Basket(){

    }

    public Basket(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

        Basket basketObject = (Basket) obj;
        return Objects.equals(this.getOrderId(), basketObject.getOrderId()) &&
                Objects.equals(this.getProductId(), basketObject.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOrderId(), this.getProductId());
    }

}
