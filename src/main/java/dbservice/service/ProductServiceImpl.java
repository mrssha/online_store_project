package dbservice.service;

import dbservice.converter.ProductConverter;
import dbservice.dao.ProductDao;
import dbservice.dto.ProductDto;
import dbservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductConverter productConverter;


    @Override
    @Transactional
    public ProductDto getById(long id) {
        return productConverter.convertToDto(productDao.getById(id));
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        return productConverter.convertToDtoList(productDao.getAll());
    }

    @Override
    @Transactional
    public List<ProductDto> getByName(String name) {
        return productConverter.convertToDtoList(productDao.getByName(name));
    }


    @Override
    @Transactional
    public List<ProductDto> getByCategory(String category) {
        return productConverter.convertToDtoList(productDao.getByCategory(category));
    }

    @Override
    @Transactional
    public List<ProductDto> getByBrand(String brand) {
        return productConverter.convertToDtoList(productDao.getByBrand(brand));
    }


    @Override
    @Transactional
    public List<ProductDto> getByParams(String name, String category, String brand, Integer minPrice, Integer maxPrice){
        return productConverter.convertToDtoList(productDao.getByParams(name, category, brand, minPrice, maxPrice));
    }

    @Override
    @Transactional
    public void add(ProductDto productDto) {
        productDao.add(productConverter.convertToEntity(productDto));

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        productDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(ProductDto productDto) {
        productDao.update(productConverter.convertToEntity(productDto));
    }
}
