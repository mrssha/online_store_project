package dbservice.dao;

import dbservice.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(long id);

    List<Product> getByName(String name);

    List<Product> getByCategory(String name);

    List<Product> getByBrand(String name);

    void add(Product customer);

    void deleteById(long id);

    void update(Product customer);

}
