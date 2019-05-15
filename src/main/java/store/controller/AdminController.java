package store.controller;

import store.dto.CategoryDto;
import store.dto.CustomerDto;
import store.dto.OrderDto;
import store.dto.ProductDto;
import store.utils.StatusResult;
import store.service.CategoryService;
import store.service.CustomerService;
import store.service.OrderService;
import store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.time.Month;
import java.util.*;


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

    @Autowired
    private ServletContext servletContext;

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



    /*
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        modelMap.addAttribute("file", file);

        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                //String rootPath = servletContext.getContextPath();

                String rootPath = "src/main/java/hello";
                File dir = new File("web" + File.separator + "resources" + File.separator + "image");
                //File dir = new File(rootPath + File.separator + "\\web\\resources\\image");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                //logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                return "You successfully uploaded file=" + name;

            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    */


    @RequestMapping( value = "/products", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("newProduct") ProductDto newProduct,
                             BindingResult bindResult,
                             @RequestParam(value = "categoryId", required = false) Long categoryId,
                             ModelMap modelMap){
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("products", productService.getAllProducts());
            return "productManager";
        }
        StatusResult result = productService.add(newProduct, categoryId);
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
        categories.sort(Comparator.comparing(CategoryDto::getCategoryName));
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
