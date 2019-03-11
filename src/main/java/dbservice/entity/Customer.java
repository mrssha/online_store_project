package dbservice.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer{

    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();
    // Нужна двусторонняя связь?

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer(){

    }

    public void addAddress(Address address){
        addresses.add(address);
        address.setCustomer(this);
    }

    // нужен ли этот метод?
    public void removeAddress(Address address){
        addresses.remove(address);
        address.setCustomer(null); //зачем ставать null?
    }

    public void addOrder(Order order){
        orders.add(order);
        order.setCustomer(this);

    }

    public void  removeOrder(Order order){
        orders.remove(order);
        order.setCustomer(null); //???
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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
        if (obj == null || obj.getClass() != Customer.class){
            return false;
        }
        Customer customer =(Customer)obj;
        return customer.getId().equals(this.getId());
    }
}