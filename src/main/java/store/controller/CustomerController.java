package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import store.dto.*;
import store.service.AddressService;
import store.service.CustomerService;
import store.service.OrderService;
import store.utils.StatusResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping( value = {"/profile", "/profile/addAddress", "/profile/changeInfo",
            "/profile/changePassword"}, method = RequestMethod.GET)
    public String customerProfile(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        modelMap.addAttribute("newAddress", new AddressDto());
        modelMap.addAttribute("changeInfo", new ChangeInfoDto());
        modelMap.addAttribute("changePass", new ChangePasswordDto());
        modelMap.addAttribute("addresses", addresses);
        return "profile";
    }


    @RequestMapping( value = "/profile/addAddress",  method = RequestMethod.POST)
    public String addAddress(@Valid @ModelAttribute("newAddress") AddressDto newAddress,
                             BindingResult bindResult, ModelMap modelMap,
                             @ModelAttribute("changeInfo") ChangeInfoDto changeInfo,
                             @ModelAttribute("changePass") ChangePasswordDto changePass,
                             HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        modelMap.addAttribute("addresses", addresses);
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("hiddenAddressForm", false);
            return "profile";
        }
        newAddress.setCustomer(customer);
        addressService.addAddress(newAddress);
        return "redirect:/profile";
    }


    @ResponseBody
    @RequestMapping( value = "/profile/deleteAddress", method = RequestMethod.POST)
    public void deleteCategory(@RequestBody String id_address){
        addressService.setActiveFalse(Long.parseLong(id_address));
    }

    @RequestMapping( value = "/profile/changeInfo",  method = RequestMethod.POST)
    public String changeInfo(@Valid @ModelAttribute("changeInfo") ChangeInfoDto newInfoCustomer,
                             BindingResult bindResult, ModelMap modelMap,
                             @ModelAttribute("newAddress") AddressDto newAddress,
                             @ModelAttribute("changePass") ChangePasswordDto changePass,
                             HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        modelMap.addAttribute("addresses", addresses);
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("hiddenInfoForm", false);
            return "profile";
        }
        CustomerDto changedCustomer = customerService.changeInfo(customer, newInfoCustomer);
        request.getSession().setAttribute("principalUser", changedCustomer);
        return "redirect:/profile";
    }

    @RequestMapping( value = "/profile/changePassword",  method = RequestMethod.POST)
    public String changePassword(@Valid @ModelAttribute("changePass") ChangePasswordDto changePass,
                                 BindingResult bindResult, ModelMap modelMap,
                                 @ModelAttribute("changeInfo") ChangeInfoDto newInfoCustomer,
                                 @ModelAttribute("newAddress") AddressDto newAddress,
                                 HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        modelMap.addAttribute("addresses", addresses);

        if (bindResult.hasErrors()) {
            modelMap.addAttribute("hiddenPassForm", false);
            return "profile";
        }
        String email = principalUser.getName();
        StatusResult result = customerService.changePassword(email, changePass);
        modelMap.addAttribute("passEditMessage", result.getMessage());
        CustomerDto changedCustomer = customerService.getCustomerByEmail(email);
        request.getSession().setAttribute("principalUser", changedCustomer);
        modelMap.addAttribute("changePass", new ChangePasswordDto());
        return "profile";
    }

    @RequestMapping( value = "/orders",  method = RequestMethod.GET)
    public String customerOrders(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<OrderDto> orders = orderService.getByCustomerId(customer.getId());
        orders.sort(Comparator.comparing(OrderDto::getDateOrder));
        modelMap.addAttribute("orders", orders);
        return "orderHistory";
    }


}
