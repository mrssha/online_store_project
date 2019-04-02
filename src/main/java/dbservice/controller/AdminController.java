package dbservice.controller;


import dbservice.dto.ProductDto;
import dbservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @RequestMapping( value = "/products", method = RequestMethod.GET)
    public String manageProducts(ModelMap modelMap){
        List<ProductDto> products = productService.getAllProducts();
        modelMap.addAttribute("products", products);
        return "productManager";
    }

    @RequestMapping( value = "/products/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("newProduct") ProductDto newProduct,
                             ModelMap modelMap){
        productService.add(newProduct);
        return "redirect:/admin/products";
    }

    @RequestMapping( value = "/categories", method = RequestMethod.GET)
    public String manageCategories(ModelMap modelMap){
        return "productManager";
    }
}
