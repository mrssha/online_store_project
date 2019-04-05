package dbservice.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbservice.dto.CategoryDto;
import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;
import dbservice.service.CategoryService;
import dbservice.service.OrderService;
import dbservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @RequestMapping( value = "/products", method = RequestMethod.GET)
    public String manageProducts(ModelMap modelMap){
        List<ProductDto> products = productService.getAllProducts();
        List<CategoryDto> categories = categoryService.getAllCategories();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("products", products);
        return "productManager";
    }

    @RequestMapping( value = "/products/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("newProduct") ProductDto newProduct,
                             @RequestParam(value = "categoryId", required = false) Long categoryId){
        CategoryDto categoryDto = categoryService.getById(categoryId);
        newProduct.setCategory(categoryDto);
        productService.add(newProduct);
        return "redirect:/admin/products";
    }


    @RequestMapping( value = "/categories", method = RequestMethod.GET)
    public String manageCategories(ModelMap modelMap){
        List<CategoryDto> categories = categoryService.getAllCategories();
        modelMap.addAttribute("categories", categories);
        return "categoryManager";
    }

    @RequestMapping( value = "/categories/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("newProduct") CategoryDto newCategory){
        categoryService.add(newCategory);
        return "redirect:/admin/categories";
    }

    @ResponseBody
    @RequestMapping( value = "/categories/delete", method = RequestMethod.POST)
    public void deleteCategory(@RequestBody String id_category){
        categoryService.deleteById(Long.parseLong(id_category));
    }

    @ResponseBody
    @RequestMapping( value = "/categories/save", method = RequestMethod.POST)
    public void updateCategory(@RequestBody String categoryJson){
        try {
            categoryService.updateFromJson(categoryJson);
        }
        catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value", e);
        }
    }

    @ResponseBody
    @RequestMapping( value = "/orders/save", method = RequestMethod.POST)
    public void updateOrder(@RequestBody String orderJson){
        System.out.println(orderJson);

        try {
            orderService.updateFromJson(orderJson);
        }
        catch (IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value", e);
        }


    }

    @ResponseBody
    @RequestMapping( value = "/products/delete", method = RequestMethod.POST)
    public void deleteProduct(@RequestBody String id_product){
        productService.deleteById(Long.parseLong(id_product));
    }

    @RequestMapping( value = "/orders", method = RequestMethod.GET)
    public String manageOrders(ModelMap modelMap){
        List<OrderDto> orders = orderService.getAllOrders();
        modelMap.addAttribute("orders", orders);
        return "orderManager";
    }
}
