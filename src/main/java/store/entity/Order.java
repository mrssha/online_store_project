package store.entity;

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

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<OrderProduct> orderProduct = new HashSet<>();


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


    public Set<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Set<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getCustomer(), order.getCustomer()) &&
                Objects.equals(getDateOrder(), order.getDateOrder()) &&
                Objects.equals(getQuantityProducts(), order.getQuantityProducts()) &&
                Objects.equals(getPayment_amount(), order.getPayment_amount()) &&
                getPaymentMethod() == order.getPaymentMethod() &&
                getDeliveryMethod() == order.getDeliveryMethod() &&
                getPaymentStatus() == order.getPaymentStatus() &&
                getOrderStatus() == order.getOrderStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getDateOrder(), getQuantityProducts(),
                getPayment_amount(), getPaymentMethod(), getDeliveryMethod(), getPaymentStatus(), getOrderStatus());
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
                '}';
    }
}
