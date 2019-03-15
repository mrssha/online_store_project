package dbservice.converter;

import dbservice.dto.ProductDto;
import dbservice.entity.Product;

import java.util.List;
import java.util.Set;


public interface ProductConverter {

    ProductDto convertToDto(Product product);

    Product convertToEntity(ProductDto productDto);

    Set<ProductDto> convertToDtoSet(Set<Product> products);

    Set<Product> convertToEntitySet(Set<ProductDto> productsDto);

    List<ProductDto> convertToDtoList(List<Product> products);

    List<Product> convertToEntityList(List<ProductDto> productsDto);

}
