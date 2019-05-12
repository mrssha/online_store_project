package dbservice.service;

import com.google.gson.Gson;
import dbservice.converter.CategoryConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.BasketDao;
import dbservice.dao.CartDao;
import dbservice.dao.OrderDao;
import dbservice.dao.ProductDao;
import dbservice.dto.CategoryDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Category;
import dbservice.result.Message;
import dbservice.result.StatusResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BasketDao basketDao;

    @Autowired
    private CartDao cartDao;

    private List<ProductDto> topProducts;

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

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
    public ProductDto getByName(String name) {
        return productConverter.convertToDto(productDao.getByName(name));
    }


    @Override
    @Transactional
    public List<ProductDto> getByCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.convertToEntity(categoryDto);
        return productConverter.convertToDtoList(productDao.getByCategory(category));
    }

    @Override
    @Transactional
    public List<ProductDto> getByBrand(String brand) {
        return productConverter.convertToDtoList(productDao.getByBrand(brand));
    }


    @Override
    @Transactional
    public List<ProductDto> getByParams(Long id_category, String brand, Integer minPrice, Integer maxPrice){
        return productConverter.convertToDtoList(productDao.getByParams(id_category, brand, minPrice, maxPrice));
    }

    @Override
    @Transactional
    public List<ProductDto> getBySearch(String search){
        return productConverter.convertToDtoList(productDao.getBySearch(search));
    }


    @Override
    @Transactional
    public List<ProductDto> getTopProducts(){
        topProducts = productConverter.convertToDtoList(productDao.getTopProducts());
        return topProducts;
    }


    @Override
    public List<ProductDto> getLastTopProductsList(){
        return topProducts;
    }


    @Override
    @Transactional
    public StatusResult add(ProductDto newProduct, Long categoryId) {
        CategoryDto categoryDto = categoryService.getById(categoryId);
        newProduct.setCategory(categoryDto);
        newProduct.setSales(0);
        ProductDto product = getByName(newProduct.getName());
        if (product == null) {
            productDao.add(productConverter.convertToEntity(newProduct));
            logger.info(String.format(StatusResult.PRODUCT_SUCCESS_CREATE.getMessage(), newProduct.getName()));
            return StatusResult.PRODUCT_SUCCESS_CREATE;
        }
        logger.info(String.format(StatusResult.PRODUCT_ALREADY_EXIST.getMessage(), newProduct.getName()));
        return StatusResult.PRODUCT_ALREADY_EXIST;
    }

    @Override
    @Transactional
    public String deleteById(long id) {
        Gson gson = new Gson();
        if (basketDao.getOrdersForProduct(id).size() ==0
                && cartDao.getCartItemsForProduct(id).size() ==0){
            productDao.deleteById(id);
            logger.info(StatusResult.PRODUCT_SUCCESS_DELETE.getMessage());
            return gson.toJson(new Message(StatusResult.PRODUCT_SUCCESS_DELETE));
        }
        return gson.toJson(new Message(StatusResult.PRODUCT_FAIL_DELETE));
    }

    @Override
    @Transactional
    public void update(ProductDto productDto) {
        productDao.update(productConverter.convertToEntity(productDto));
    }
}
