package dbservice.service;

import dbservice.dto.CategoryDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    CategoryDto getById(long id);

    List<CategoryDto> getAllCategories();

    void add(CategoryDto category);

    void deleteById(long id);

    void update(CategoryDto category);

    void updateFromJson(String categoryJson) throws IOException;
}

