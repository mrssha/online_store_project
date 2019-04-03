package dbservice.converter;


import dbservice.dto.CategoryDto;
import dbservice.entity.Category;

public interface CategoryConverter {

    CategoryDto convertToDto(Category category);

    Category convertToEntity(CategoryDto categoryDto);

}
