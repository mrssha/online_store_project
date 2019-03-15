package dbservice.service;

import dbservice.dto.ProductDto;
import dbservice.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto getById(long id);

    List<ProductDto> getByName(String name);

    List<ProductDto> getByCategory(String category);

    List<ProductDto> getByBrand(String brand);

    List<ProductDto> getByParams(String name, String category, String brand);

    void add(ProductDto product);

    void deleteById(long id);

    void update(ProductDto product);
}
