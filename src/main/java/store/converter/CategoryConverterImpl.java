package store.converter;


import store.dto.CategoryDto;
import store.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements CategoryConverter {

    @Override
    public CategoryDto convertToDto(Category category){
        if (category == null){
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }

    @Override
    public Category convertToEntity(CategoryDto categoryDto){
        if (categoryDto== null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
