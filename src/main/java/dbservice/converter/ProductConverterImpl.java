package dbservice.converter;

import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Order;
import dbservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("productConverter")
public class ProductConverterImpl implements ProductConverter {

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setBrand(product.getBrand());
        productDto.setWeight(product.getWeight());
        productDto.setSex(product.getSex());
        productDto.setImageSm(product.getImageSm());
        productDto.setImageBg(product.getImageBg());
        //productDto.setDescription(product.getDescription());
        return productDto;
    }

    @Override
    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setBrand(productDto.getBrand());
        product.setWeight(productDto.getWeight());
        product.setSex(productDto.getSex());
        product.setImageSm(productDto.getImageSm());
        product.setImageBg(productDto.getImageBg());
        return product;
    }

    @Override
    public Set<ProductDto> convertToDtoSet(Set<Product> products) {
        Set<ProductDto> productsDto = new HashSet<>();
        for (Product product: products){
            productsDto.add(convertToDto(product));
        }
        return productsDto;
    }

    @Override
    public Set<Product> convertToEntitySet(Set<ProductDto> productsDto) {
        Set<Product> products = new HashSet<>();
        for (ProductDto productDto: productsDto){
            products.add(convertToEntity(productDto));
        }
        return products;
    }

    @Override
    public List<ProductDto> convertToDtoList(List<Product> products) {
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product: products){
            productsDto.add(convertToDto(product));
        }
        return productsDto;
    }

    @Override
    public List<Product> convertToEntityList(List<ProductDto> productsDto) {
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto: productsDto){
            products.add(convertToEntity(productDto));
        }
        return products;
    }
}


