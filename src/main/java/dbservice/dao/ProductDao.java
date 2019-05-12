package dbservice.dao;

import dbservice.entity.Category;
import dbservice.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(long id);

    Product getByIdForUpdate(long id);

    List<Product> getAll();

    Product getByName(String name);

    List<Product> getByCategory(Category category);

    List<Product> getByBrand(String name);

    List<Product> getByParams(Long id_category, String brand,
                              Integer minPrice, Integer maxPrice);

    List<Product> getBySearch(String search);

    List<Product> getTopProducts();

    void add(Product product);

    void deleteById(long id);

    void update(Product product);

}
