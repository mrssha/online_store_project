package dbservice.controller;


import dbservice.dto.*;
import dbservice.entity.Address;
import dbservice.entity.DeliveryMethod;
import dbservice.service.AddressService;
import dbservice.service.CartService;
import dbservice.service.CustomerService;
import dbservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;


    @RequestMapping( value = "/order", method = RequestMethod.GET)
    public String createOrder(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();

        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        List<CartDto> cartItems = cartService.getCartItemsByCustomersEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());

        int amount = 0;
        double total = 0;
        for(CartDto cart: cartItems){
            amount += cart.getQuantity();
            total += cart.getProduct().getPrice() * cart.getQuantity();
        }
        modelMap.addAttribute("addresses", addresses);
        modelMap.addAttribute("amount", amount);
        modelMap.addAttribute("total", total);
        return "order";
    }


    @RequestMapping( value = "/order/confirmOrder", method = RequestMethod.POST)
    public String confirmOrder(Model model, HttpServletRequest request,
                               @ModelAttribute("newOrder") OrderDto newOrder){
        Principal principalUser = request.getUserPrincipal();
        List<CartDto> cartItems = cartService.getCartItemsByCustomersEmail(principalUser.getName());
        if (cartService.checkMissingItems(cartItems).size() != 0){
            return "redirect:/cart";
        }
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        newOrder.setCustomer(customer);
        orderService.confirmOrder(newOrder, cartItems);

        return "orderSuccess";
    }









    @RequestMapping( value = "/confirm", method = RequestMethod.GET)
    public String confirm(Model model, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        List<CartDto> cartItems = cartService.getCartItemsByCustomersEmail(principalUser.getName());
        int amount = 0;
        double total = 0;
        for(CartDto cart: cartItems){
            amount += cart.getQuantity();
            total += cart.getProduct().getPrice() * cart.getQuantity();
        }
        model.addAttribute("amount", amount);
        model.addAttribute("total", total);
        return "order";
    }


}
