package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import store.dto.CategoryDto;
import store.dto.CustomerDto;
import store.dto.OrderDto;
import store.dto.ProductDto;
import store.service.CategoryService;
import store.service.CustomerService;
import store.service.OrderService;
import store.service.ProductService;
import store.utils.StatusResult;
import javax.validation.Valid;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping( value = "/products", method = RequestMethod.GET)
    public String manageProducts(ModelMap modelMap){
        List<ProductDto> products = productService.getAllProducts();
        List<CategoryDto> categories = categoryService.getAllCategories();
        products.sort(Comparator.comparing(ProductDto::getId));
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("newProduct", new ProductDto());
        return "productManager";
    }

    @RequestMapping( value = "/products", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("newProduct") ProductDto newProduct,
                             BindingResult bindResult,
                             @RequestParam(value = "categoryId", required = false) Long categoryId,
                             @RequestParam("fileSm") MultipartFile fileSm,
                             @RequestParam("fileBg") MultipartFile fileBg,
                             ModelMap modelMap){
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("products", productService.getAllProducts());
            return "productManager";
        }
        StatusResult result = productService.add(newProduct, categoryId, fileSm,  fileBg);
        if (result == StatusResult.PRODUCT_ALREADY_EXIST){
            modelMap.addAttribute("message",
                    String.format(result.getMessage(), newProduct.getName()));
        }
        modelMap.addAttribute("products", productService.getAllProducts());
        modelMap.addAttribute("newProduct", new ProductDto());
        return "productManager";
    }

    @ResponseBody
    @RequestMapping( value = "/products/delete", method = RequestMethod.POST)
    public String  deleteProduct(@RequestBody String id_product){
        return productService.deleteById(Long.parseLong(id_product));
    }


    @RequestMapping( value = "/categories", method = RequestMethod.GET)
    public String manageCategories(ModelMap modelMap){
        List<CategoryDto> categories = categoryService.getAllCategories();
        categories.sort(Comparator.comparing(CategoryDto::getId));
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("newCategory", new CategoryDto());
        return "categoryManager";
    }

    @RequestMapping( value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid @ModelAttribute("newCategory") CategoryDto newCategory,
                              BindingResult bindResult, ModelMap modelMap){
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("categories", categoryService.getAllCategories());
            return "categoryManager";
        }
        StatusResult result = categoryService.add(newCategory);
        if (result == StatusResult.CATEGORY_ALREADY_EXIST){
            modelMap.addAttribute("message",
                    String.format(result.getMessage(), newCategory.getCategoryName()));
        }
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        modelMap.addAttribute("newCategory", new CategoryDto());
        return "categoryManager";
    }

    @ResponseBody
    @RequestMapping( value = "/categories/delete", method = RequestMethod.POST)
    public String deleteCategory(@RequestBody String id_category){
        return categoryService.deleteById(Long.parseLong(id_category));
    }

    @ResponseBody
    @RequestMapping( value = "/categories/save", method = RequestMethod.POST)
    public String  updateCategory(@RequestBody String categoryJson){
    return categoryService.updateCategory(categoryJson);
    }

    @ResponseBody
    @RequestMapping( value = "/orders/save", method = RequestMethod.POST)
    public void updateOrder(@RequestBody String statusJson){
        orderService.updateStatus(statusJson);
    }


    @RequestMapping( value = "/orders", method = RequestMethod.GET)
    public String manageOrders(ModelMap modelMap){
        List<OrderDto> orders = orderService.getAllOrders();
        orders.sort(Comparator.comparing(OrderDto::getDateOrder)
                .thenComparing(OrderDto::getId));
        modelMap.addAttribute("customer", customerService.getAllCustomers());
        modelMap.addAttribute("orders", orders);
        return "orderManager";
    }


    @RequestMapping( value = "/statistics", method = RequestMethod.GET)
    public String showStatistics(ModelMap modelMap){
        List<ProductDto> products = productService.getTopProducts();
        List<CustomerDto> customersTop = customerService.getTopCustomers();
        products.sort(Comparator.comparing(ProductDto::getSales).reversed());
        customersTop.sort(Comparator.comparing(CustomerDto::getSumPurchases).reversed());
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("customersTop", customersTop);
        modelMap.addAttribute("years", Arrays.asList(2019, 2018, 2017));
        modelMap.addAttribute("months", Month.values());
        modelMap.addAttribute("revenueWeek", orderService.getRevenueForWeek());
        return "statistics";
    }

    @ResponseBody
    @RequestMapping( value = "/statistics/calculate", method = RequestMethod.POST)
    public String calculateRevenue(@RequestBody String periodJson){
        return orderService.getRevenueForPeriod(periodJson);
    }
}
