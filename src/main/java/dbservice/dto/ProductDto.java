package dbservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ProductDto {

    private Long id;

    @NotEmpty(message = "Category shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String name;

    @NotNull(message = "Price shouldn't be empty")
    @Positive(message = "Price should be greater than 0")
    private Integer price;

    private CategoryDto category;

//    @NotEmpty(message = "Product name shouldn't be empty")
//    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String brand;

    @Positive(message = "Weight should be greater than 0")
    private Double weight;

    private String sex;

    @NotNull(message = "Quantity shouldn't be empty")
    @Positive(message = "Quantity should be greater than 0")
    private Integer quantity;

    private String imageSm;
    private String imageBg;
    private Integer sales;

    public ProductDto(){
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass()!= ProductDto.class){
            return false;
        }
        ProductDto product = (ProductDto) obj;
        return product.getId().equals(this.getId());
    }


    @Override
    public String toString() {
        return "ProductDto{" +
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
