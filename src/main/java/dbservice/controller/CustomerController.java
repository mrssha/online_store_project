package dbservice.controller;

import dbservice.dto.*;
import dbservice.entity.AddressType;
import dbservice.service.AddressService;
import dbservice.service.CustomerService;
import dbservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
        newAddress.setAddressType(AddressType.CUSTOMER);
        addressService.addAddress(newAddress);
        return "redirect:/profile";
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
        return "profile";
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
        String message = customerService.changePassword(email, changePass);

        if (message.equals("INVALID_OLD_PASSWORD")){
            modelMap.addAttribute("passEditMessage", "Invalid old password");
        }else{
            modelMap.addAttribute("passEditMessage", "Password changed successfully");
        }
        CustomerDto changedCustomer = customerService.getCustomerByEmail(email);
        request.getSession().setAttribute("principalUser", changedCustomer);
        return "profile";
    }

    @RequestMapping( value = "/orders",  method = RequestMethod.GET)
    public String customerOrders(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<OrderDto> orders = orderService.getByCustomerId(customer.getId());
        modelMap.addAttribute("orders", orders);
        return "orderHistory";
    }


}
