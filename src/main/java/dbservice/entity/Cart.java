package dbservice.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "id_cart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public Cart(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return quantity == cart.quantity &&
                id.equals(cart.id) &&
                customer.equals(cart.customer) &&
                product.equals(cart.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, product, quantity);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
