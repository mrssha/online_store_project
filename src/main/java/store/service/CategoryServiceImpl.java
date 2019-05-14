package store.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import store.converter.CategoryConverter;
import store.dao.CategoryDao;
import store.dao.ProductDao;
import store.dto.CategoryDto;
import store.entity.Category;
import store.entity.Product;
import store.result.StatusResult;
import store.result.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductDao productDao;

    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    @Transactional
    public CategoryDto getById(long id){
        return categoryConverter.convertToDto(categoryDao.getById(id));

    }

    @Override
    @Transactional
    public CategoryDto getByName(String name){
        return categoryConverter.convertToDto(categoryDao.getByName(name));
    }

    @Override
    @Transactional
    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryDao.getAllCategories();
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for (Category category: categories){
            categoriesDto.add(categoryConverter.convertToDto(category));
        }
        return categoriesDto;
    }



    @Override
    @Transactional
    public String updateCategory (String categoryJson){
        JsonElement jElement = new JsonParser().parse(categoryJson);
        JsonObject jObject = jElement.getAsJsonObject();
        Long id = jObject.get("id").getAsLong();
        String name = jObject.get("name").getAsString();
        CategoryDto category = getByName(name);
        CategoryDto updatedCategory = getById(id);
        Gson gson = new Gson();
        if (category == null || category.getId().equals(id)){
            updatedCategory.setCategoryName(name);
            update(updatedCategory);
            String message = StatusResult.CATEGORY_SUCCESS_UPDATE.getFormatMessage(name);
            logger.info(message);
            return gson.toJson(new Message(StatusResult.CATEGORY_SUCCESS_UPDATE, message));
        }
        String message = StatusResult.CATEGORY_ALREADY_EXIST.getFormatMessage(name);
        logger.info(message);
        //JsonObject object = new JsonObject();
        //object.addProperty("resultType", StatusResult.CATEGORY_ALREADY_EXIST.name());
        //object.addProperty("message", message);
        //object.addProperty("oldName", updatedCategory.getCategoryName());
        //return gson.toJson(object);
        Message jsonMessage = new Message(StatusResult.CATEGORY_ALREADY_EXIST, message);
        jsonMessage.getDataMap().put("oldName", updatedCategory.getCategoryName());
        return gson.toJson(jsonMessage);
    }

    @Override
    @Transactional
    public StatusResult add(CategoryDto categoryDto){
        CategoryDto category = getByName(categoryDto.getCategoryName());
        if (category == null) {
            categoryDao.add(categoryConverter.convertToEntity(categoryDto));
            logger.info(String.format(StatusResult.CATEGORY_SUCCESS_CREATE.getMessage(), categoryDto.getCategoryName()));
            return StatusResult.CATEGORY_SUCCESS_CREATE;
        }
        logger.info(String.format(StatusResult.CATEGORY_ALREADY_EXIST.getMessage(), categoryDto.getCategoryName()));
        return StatusResult.CATEGORY_ALREADY_EXIST;

    }

    @Override
    @Transactional
    public String deleteById(long id){
        Gson gson = new Gson();
        CategoryDto categoryDto = getById(id);
        List<Product> products = productDao.getByCategory(categoryConverter.convertToEntity(categoryDto));
        if (products.size() == 0) {
            categoryDao.deleteById(id);
            String message = StatusResult.CATEGORY_SUCCESS_DELETE.getFormatMessage(categoryDto.getCategoryName());
            logger.info(message);
            return gson.toJson(new Message(StatusResult.CATEGORY_SUCCESS_DELETE, message));
        }
        String message = StatusResult.CATEGORY_FAIL_DELETE.getFormatMessage(categoryDto.getCategoryName());
        logger.info(message);
        return gson.toJson(new Message(StatusResult.CATEGORY_FAIL_DELETE, message));
    }

    @Override
    @Transactional
    public void update(CategoryDto categoryDto){
        categoryDao.update(categoryConverter.convertToEntity(categoryDto));
    }
}
