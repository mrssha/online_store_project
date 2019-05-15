package store.service;

import org.springframework.web.multipart.MultipartFile;
import store.dto.CategoryDto;
import store.dto.ProductDto;
import store.utils.StatusResult;

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

    StatusResult add(ProductDto product, Long categoryId,  MultipartFile imageSm,  MultipartFile imageBg);

    String deleteById(long id);

    void update(ProductDto product);
}
