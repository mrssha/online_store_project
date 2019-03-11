package dbservice.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "sex")
    private String sex;

    @Column(name = "quantity")
    private Integer quantity;

    /*
    // Разбивваем на ManyToOne + OneToMany
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Order> orders;
    */
    //нужен геттер сеттер?


    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Basket> orders = new ArrayList<>();
    //private List<Basket> orders = new ArrayList<>();

    public Product(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Basket> getOrders() {
        return orders;
    }

    public void setOrders(List<Basket> orders) {
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
        if (obj == null || obj.getClass()!= Product.class){
            return false;
        }
        Product product = (Product) obj;
        return product.getId().equals(this.getId());
    }
}
