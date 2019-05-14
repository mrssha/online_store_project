package store.service;

import store.dto.CategoryDto;
import store.result.StatusResult;

import java.util.List;

public interface CategoryService {

    CategoryDto getById(long id);

    CategoryDto getByName(String name);

    List<CategoryDto> getAllCategories();

    StatusResult add(CategoryDto category);

    String deleteById(long id);

    void update(CategoryDto category);

    String updateCategory(String categoryJson);
}

