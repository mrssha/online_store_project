package dbservice.controller;

import dbservice.dto.AddressDto;
import dbservice.dto.CustomerDto;
import dbservice.service.AddressService;
import dbservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @RequestMapping( value = "/profile",  method = RequestMethod.GET)
    public String customerProfile(ModelMap modelMap, HttpServletRequest request){
        //CustomerDto customer = (CustomerDto) request.getSession().getAttribute("principalUser");
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        modelMap.addAttribute("newAddress", new AddressDto());
        modelMap.addAttribute("changeInfo", new CustomerDto());
        modelMap.addAttribute("addresses", addresses);
        return "profile";
    }


    @RequestMapping( value = "/profile/addAddress",  method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("newAddress") AddressDto newAddress,
                             HttpServletRequest request){
        // Validation service
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        newAddress.setCustomer(customer);
        addressService.addAddress(newAddress);
        return "redirect:/profile";
    }

    @RequestMapping( value = "/profile/changeInfo",  method = RequestMethod.POST)
    public String changeInfo(@ModelAttribute("changeInfo") CustomerDto newInfoCustomer,
                             HttpServletRequest request){
        // Validation service
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        CustomerDto changedCustomer = customerService.changeInfo(customer, newInfoCustomer);
        //userDetailsService.loadUserByUsername(changedCustomer.getEmail());
        request.getSession().setAttribute("principalUser", changedCustomer);
        return "redirect:/profile";
    }

    @RequestMapping( value = "/profile/changePassword",  method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword,
                                 HttpServletRequest request, ModelMap modelMap){
        // Validation service
        Principal principalUser = request.getUserPrincipal();
        String email = principalUser.getName();
        String message = customerService.changePassword(email, oldPassword, newPassword);
        if (message.equals("INVALID_OLD_PASSWORD")){
            modelMap.addAttribute("passEditMessage", "Invalid old password");
        }
        modelMap.addAttribute("passEditMessage", "Password changed successfully");
        CustomerDto changedCustomer = customerService.getCustomerByEmail(email);
        request.getSession().setAttribute("principalUser", changedCustomer);
        return "redirect:/profile";
    }


}
