package store.entity;

import javax.persistence.*;
import java.util.*;

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
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "sex")
    private String sex;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image_sm")
    private String imageSm;

    @Column(name = "image_bg")
    private String imageBg;

    @Column(name = "sales")
    private Integer sales;


    /*
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<Basket> basket = new HashSet<>();
    */

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<Cart> cart = new HashSet<>();

    public Product(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public String getImageSm() {
        return imageSm;
    }

    public void setImageSm(String imageSm) {
        this.imageSm = imageSm;
    }

    public String getImageBg() {
        return imageBg;
    }

    public void setImageBg(String imageBg) {
        this.imageBg = imageBg;
    }

    /*
    public Set<Basket> getBasket() {
        return basket;
    }

    public void setBasket(Set<Basket> basket) {
        this.basket = basket;
    }
    */

    public Set<Cart> getCart() {
        return cart;
    }

    public void setCart(Set<Cart> cart) {
        this.cart = cart;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    /*
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
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getBrand(), product.getBrand()) &&
                Objects.equals(getWeight(), product.getWeight()) &&
                Objects.equals(getSex(), product.getSex()) &&
                Objects.equals(getQuantity(), product.getQuantity()) &&
                Objects.equals(getImageSm(), product.getImageSm()) &&
                Objects.equals(getImageBg(), product.getImageBg()) &&
                Objects.equals(getSales(), product.getSales());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand='" + brand + '\'' +
                ", weight=" + weight +
                ", sex='" + sex + '\'' +
                ", quantity=" + quantity +
                ", imageSm='" + imageSm + '\'' +
                ", imageBg='" + imageBg + '\'' +
                '}';
    }
}
