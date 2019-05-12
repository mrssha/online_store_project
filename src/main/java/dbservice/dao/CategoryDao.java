package dbservice.dao;

import dbservice.entity.Category;

import java.util.List;

public interface CategoryDao {

    Category getById(long id);

    Category getByName(String name);

    List<Category> getAllCategories();

    void add(Category category);

    void deleteById(long id);

    void update(Category category);
}
