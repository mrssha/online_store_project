package dbservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BasketId implements Serializable {

    @Column(name = "id_order")
    private Long orderId;

    @Column(name = "id_product")
    private Long productId;

    public BasketId(){
    }

    public BasketId(Long orderId, Long productId) {
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BasketId basketIdObject = (BasketId) obj;
        return Objects.equals(this.getOrderId(), basketIdObject.getOrderId()) &&
                Objects.equals(this.getProductId(), basketIdObject.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOrderId(), this.getProductId());
    }

}
