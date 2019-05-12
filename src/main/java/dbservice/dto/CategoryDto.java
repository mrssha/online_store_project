package dbservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;


public class CategoryDto {

    private Long id;

    @NotEmpty(message = "Category shouldn't be empty")
    @Size(min = 2, max = 45, message = "Field should contain from 2 to 45 characters")
    private String categoryName;

    public CategoryDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto that = (CategoryDto) o;
        return id.equals(that.id) &&
                categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
