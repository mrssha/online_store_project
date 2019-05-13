package dbservice.service;

import dbservice.dto.CategoryDto;
import dbservice.dto.ProductDto;
import dbservice.result.StatusResult;

import java.util.List;

public interface ProductService {

    ProductDto getById(long id);

    List<ProductDto> getAllProducts();

    ProductDto getByName(String name);

    List<ProductDto> getByCategory(CategoryDto categoryDto);

    List<ProductDto> getByBrand(String brand);

    List<ProductDto> getByParams(Long id_category, String brand,
                                 Integer minPrice, Integer maxPrice);

    List<ProductDto> getBySearch(String search);

    List<ProductDto> getTopProducts();

    StatusResult add(ProductDto product, Long categoryId);

    String deleteById(long id);

    void update(ProductDto product);
}
