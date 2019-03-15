package dbservice.entity;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer_address")
    private Address customerAddress;
    // Связь односторонняя

    @Column(name = "date_order", nullable = false)
    private Date dateOrder;

    @Column(name = "quantity_products")
    private Integer quantityProducts;

    @Column(name = "payment_amount")
    private Double payment_amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", columnDefinition = "CASH")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method", columnDefinition = "SELF_DELIVERY")
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", columnDefinition = "WAITING")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "WAIT_PAYMENT")
    private OrderStatus orderStatus;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns ({
        @JoinColumn(name = "id_customer", referencedColumnName = "id_customer"),
        @JoinColumn(name = "id_customer_address",referencedColumnName = "id_address")
    })
    private Customer customer;
    */

    /*
    // Разделяем на OneToMany + ManyToOne
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Basket",
            joinColumns =@JoinColumn(name = "id_order", referencedColumnName = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    )
    private Set<Product> products;
    */


    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Basket> basket = new HashSet<>();



    public Order(){
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

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(Integer quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

    public Double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<Basket> getBasket() {
        return basket;
    }

    public void setBasket(Set<Basket> basket) {
        this.basket = basket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass()!= Order.class){
            return false;
        }
        Order order = (Order)obj;
        return order.getId().equals(this.getId());
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", customerAddress=" + customerAddress +
                ", dateOrder=" + dateOrder +
                ", quantityProducts=" + quantityProducts +
                ", payment_amount=" + payment_amount +
                ", paymentMethod=" + paymentMethod +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                ", orderStatus=" + orderStatus +
                ", basket=" + basket +
                '}';
    }
}
