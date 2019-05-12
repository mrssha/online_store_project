package dbservice.controller;

import dbservice.dto.CategoryDto;
import dbservice.dto.ProductDto;
import dbservice.service.CategoryService;
import dbservice.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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
        List<ProductDto> products = productService.getByParams(id_category, brand, minPrice, maxPrice);
        model.addAttribute("selectedProducts", products);
        return "home";
    }

    @GetMapping("/search")
    public String getProductBySearch(@RequestParam(value = "search", required = false) @NotEmpty String search,
                                     ModelMap model){
        if (!search.equals("")){
            List<ProductDto> products = productService.getBySearch(search);
            model.addAttribute("selectedProducts", products);
        }
        return "home";
    }


}
