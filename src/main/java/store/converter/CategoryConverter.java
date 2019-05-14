package store.converter;


import store.dto.CategoryDto;
import store.entity.Category;

public interface CategoryConverter {

    CategoryDto convertToDto(Category category);

    Category convertToEntity(CategoryDto categoryDto);

}
