package store.service;

import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;
import store.converter.CategoryConverter;
import store.converter.ProductConverter;
import store.dao.OrderProductDao;
import store.dao.CartDao;
import store.dao.ProductDao;
import store.dto.CategoryDto;
import store.dto.ProductDto;
import store.entity.Category;
import store.utils.LogMessage;
import store.utils.Message;
import store.utils.StatusResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private ProductConverter productConverter;
    private CategoryConverter categoryConverter;
    private CategoryService categoryService;
    private OrderProductDao orderProductDao;
    private CartDao cartDao;

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
        return productConverter.convertToDtoList(productDao.getTopProducts());
    }


    @Override
    @Transactional
    public StatusResult add(ProductDto newProduct, Long categoryId, MultipartFile imageSm,  MultipartFile imageBg) {
        ProductDto product = getByName(newProduct.getName());
        if (product == null) {
            CategoryDto categoryDto = categoryService.getById(categoryId);
            newProduct.setCategory(categoryDto);
            newProduct.setSales(0);
            loadImages(newProduct, imageSm, imageBg);
            productDao.add(productConverter.convertToEntity(newProduct));
            logger.info(String.format(StatusResult.PRODUCT_SUCCESS_CREATE.getMessage(), newProduct.getName()));
            return StatusResult.PRODUCT_SUCCESS_CREATE;
        }
        logger.info(String.format(StatusResult.PRODUCT_ALREADY_EXIST.getMessage(), newProduct.getName()));
        return StatusResult.PRODUCT_ALREADY_EXIST;
    }

    private void loadImages(ProductDto newProduct, MultipartFile imageSm,  MultipartFile imageBg){
        if (!imageSm.isEmpty()) {
            String nameSm = imageSm.getOriginalFilename();
            newProduct.setImageSm(nameSm);
        } else {
            logger.warn(LogMessage.IMAGE_LOAD_FAILD);
        }
        if (!imageBg.isEmpty()) {
            String nameBg = imageBg.getOriginalFilename();
            newProduct.setImageBg(nameBg);
        } else {
            logger.warn(LogMessage.IMAGE_LOAD_FAILD);
        }
    }

    @Override
    @Transactional
    public String deleteById(long id) {
        Gson gson = new Gson();
        if (orderProductDao.getOrdersForProduct(id).size() ==0
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


    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setCategoryConverter(CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setOrderProductDao(OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Autowired
    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }
}
