package dbservice.dto;

import dbservice.entity.*;


import java.util.Date;
import java.util.Objects;

public class OrderDto {

    private Long id;

    private CustomerDto customer;

    private AddressDto customerAddress;

    private Date dateOrder;

    private Integer quantityProducts;

    private Double payment_amount;

    private PaymentMethod paymentMethod;

    private DeliveryMethod deliveryMethod;

    private PaymentStatus paymentStatus;

    private OrderStatus orderStatus;


    public OrderDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public AddressDto getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(AddressDto customerAddress) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(getId(), orderDto.getId()) &&
                Objects.equals(getCustomer(), orderDto.getCustomer()) &&
                Objects.equals(getDateOrder(), orderDto.getDateOrder()) &&
                Objects.equals(getQuantityProducts(), orderDto.getQuantityProducts()) &&
                Objects.equals(getPayment_amount(), orderDto.getPayment_amount()) &&
                getPaymentMethod() == orderDto.getPaymentMethod() &&
                getDeliveryMethod() == orderDto.getDeliveryMethod() &&
                getPaymentStatus() == orderDto.getPaymentStatus() &&
                getOrderStatus() == orderDto.getOrderStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getDateOrder(), getQuantityProducts(), getPayment_amount(), getPaymentMethod(), getDeliveryMethod(), getPaymentStatus(), getOrderStatus());
    }

    @Override
    public String toString() {
        return "OrderDto{" +
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
