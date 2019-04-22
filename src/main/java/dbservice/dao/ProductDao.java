package dbservice.dao;

import dbservice.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(long id);

    List<Product> getAll();

    List<Product> getByName(String name);

    List<Product> getByCategory(String name);

    List<Product> getByBrand(String name);

    List<Product> getByParams(String name, Long id_category, String brand,
                              Integer minPrice, Integer maxPrice);

    List<Product> getTopProducts();

    void add(Product product);

    void deleteById(long id);

    void update(Product product);

}
