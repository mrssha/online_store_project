package dbservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbservice.converter.CategoryConverter;
import dbservice.dao.CategoryDao;
import dbservice.dto.CategoryDto;
import dbservice.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    @Transactional
    public CategoryDto getById(long id){
        return categoryConverter.convertToDto(categoryDao.getById(id));

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
    public void updateFromJson (String categoryJson) throws IOException {


        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(categoryJson);
        System.out.println(rootNode);
        Long id = rootNode.get("id").asLong();
        String name = rootNode.get("name").asText();

        CategoryDto categoryDto = getById(id);
        categoryDto.setCategoryName(name);
        update(categoryDto);


    }

    @Override
    @Transactional
    public void add(CategoryDto categoryDto){
        categoryDao.add(categoryConverter.convertToEntity(categoryDto));
    }

    @Override
    @Transactional
    public void deleteById(long id){
        categoryDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(CategoryDto categoryDto){
        categoryDao.update(categoryConverter.convertToEntity(categoryDto));
    }
}