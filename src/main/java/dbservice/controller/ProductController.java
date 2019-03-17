package dbservice.controller;


import dbservice.dto.ProductDto;
import dbservice.entity.Product;
import dbservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    ProductService productService;

    /*
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model){
        ProductDto product = productService.getById(id);
        model.addAttribute("name", product.getName());
        model.addAttribute(product.getBrand());
        model.addAttribute("price", product.getPrice());
        return "product_detail";
    }



    @GetMapping("/hello")
    public String getProductByParams(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "brand", required = false) String brand,
                                     @RequestParam(value = "category", required = false) String category){
        List<ProductDto> products = productService.getByParams(name, brand, category);
        //model.addAttribute("name", product.getName());
        //model.addAttribute(product.getBrand());
        //model.addAttribute("price", product.getPrice());
        return "product_detail";
    }
    */

    //@GetMapping("/list")
    /*
    @RequestMapping( method = RequestMethod.GET)
    public List<ProductDto> listProducts(@RequestParam(value = "name", required = false) String name) {

        return productService.getByName("name");
    }
    */


    @RequestMapping( method = RequestMethod.GET)
    public String printHello(ModelMap model){
        System.out.println("I m here!!!!!!!!!");
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello"; //имя вида
    }

}
