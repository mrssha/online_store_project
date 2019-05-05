package dbservice.controller;


import dbservice.dto.CartDto;
import dbservice.dto.CategoryDto;
import dbservice.dto.CustomerDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Product;
import dbservice.service.CartService;
import dbservice.service.CategoryService;
import dbservice.service.CustomerService;
import dbservice.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class ProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = Logger.getLogger(ProductController.class);

    @ResponseBody
    @RequestMapping(value = "product/top", method = RequestMethod.GET)
    public List<ProductDto> getTopProducts(){
        return productService.getTopProducts();
    }


    @RequestMapping( method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, ModelMap modelMap, Exception ex){
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<ProductDto> products = productService.getTopProducts();
        HttpSession session = request.getSession();
        session.setAttribute ("categories", categories);
        session.setAttribute ("topProducts", products);
        return "home";
    }

    @GetMapping("product/{id}")
    public String getProductById(@PathVariable Long id, Model model){
        ProductDto product = productService.getById(id);
        model.addAttribute("productDetail", product);
        return "productDetail";
    }


    @GetMapping("/filter")
    public String getProductByParams(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "category", required = false) Long id_category,
                                     @RequestParam(value = "brand", required = false) String brand,
                                     @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                     @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                     ModelMap model){
        List<ProductDto> products = productService.getByParams(name, id_category, brand, minPrice, maxPrice);
        model.addAttribute("selectedProducts", products);
        return "home";
    }


}
