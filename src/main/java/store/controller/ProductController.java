package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import store.dto.CategoryDto;
import store.dto.ProductDto;
import store.service.CategoryService;
import store.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController{

    private ProductService productService;
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "product/top", method = RequestMethod.GET)
    public List<ProductDto> getTopProducts(){
        return productService.getTopProducts();
    }


    @RequestMapping( method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, ModelMap modelMap, Exception ex){
        List<CategoryDto> categories = categoryService.getAllCategories();
        HttpSession session = request.getSession();
        session.setAttribute ("categories", categories);
        return "home";
    }

    @GetMapping("product/{id}")
    public String getProductById(@PathVariable Long id, Model model){
        ProductDto product = productService.getById(id);
        model.addAttribute("productDetail", product);
        return "productDetail";
    }


    @GetMapping("/filter")
    public String getProductByParams(@RequestParam(value = "category", required = false) Long id_category,
                                     @RequestParam(value = "brand", required = false) String brand,
                                     @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                     @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                     ModelMap model){
        List<ProductDto> products = productService.getByParams(id_category, brand, minPrice, maxPrice);
        products.sort(Comparator.comparing(ProductDto::getId));
        model.addAttribute("selectedProducts", products);
        return "home";
    }

    @GetMapping("/search")
    public String getProductBySearch(@RequestParam(value = "search", required = false) @NotEmpty String search,
                                     ModelMap model){
        if (!search.equals("")){
            List<ProductDto> products = productService.getBySearch(search);
            products.sort(Comparator.comparing(ProductDto::getId));
            model.addAttribute("selectedProducts", products);
        }
        return "home";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
