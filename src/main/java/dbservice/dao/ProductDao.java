package dbservice.dao;

import dbservice.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(long id);

    List<Product> getByName(String name);

    List<Product> getByCategory(String name);

    List<Product> getByBrand(String name);

    List<Product> getByParams(String name, String category, String brand);

    void add(Product product);

    void deleteById(long id);

    void update(Product product);

}
