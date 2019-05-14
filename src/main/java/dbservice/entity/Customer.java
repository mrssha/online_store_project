package dbservice.entity;

import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "sum_purchases")
    private Double sumPurchases;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<Cart> cart = new HashSet<>();

    public Customer(){

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

    public Double getSumPurchases() {
        return sumPurchases;
    }

    public void setSumPurchases(Double sumPurchases) {
        this.sumPurchases = sumPurchases;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getSecondName(), customer.getSecondName()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getPassword(), customer.getPassword()) &&
                Objects.equals(getBirthDate(), customer.getBirthDate()) &&
                Objects.equals(getSumPurchases(), customer.getSumPurchases()) &&
                Objects.equals(getRole(), customer.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName(), getEmail(), getPassword(),
                getBirthDate(), getSumPurchases(), getRole());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", role='" + role + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}