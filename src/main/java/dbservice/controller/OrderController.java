package dbservice.controller;


import dbservice.dto.*;
import dbservice.entity.Address;
import dbservice.entity.AddressType;
import dbservice.entity.DeliveryMethod;
import dbservice.result.StatusResult;
import dbservice.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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

    private static final Logger logger = Logger.getLogger(OrderController.class);


    @RequestMapping( value = "/order", method = RequestMethod.GET)
    public String createOrder(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        BaseCartDto baseCartDto = cartService.getBaseCartByCustomersEmail(principalUser.getName());
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        List<AddressDto> pickupPoints = addressService.getByAddressType(AddressType.PICKUP);
        modelMap.addAttribute("addresses", addresses);
        modelMap.addAttribute("pickupPoints", pickupPoints);
        modelMap.addAttribute("amount", baseCartDto.getAmountProducts());
        modelMap.addAttribute("total", baseCartDto.getTotalSum());
        return "order";
    }



    @RequestMapping( value = "/order/confirmOrder", method = RequestMethod.POST)
    public String confirmOrder(Model model, HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "deliveryAddress") Long address_id,
                               @ModelAttribute("newOrder") OrderDto newOrder)
            throws UnsupportedEncodingException {
        Principal principalUser = request.getUserPrincipal();
        BaseCartDto baseCartDto = cartService.getBaseCartByCustomersEmail(principalUser.getName());
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        newOrder.setCustomer(customer);
        AddressDto address = addressService.getAddressById(address_id);
        newOrder.setCustomerAddress(address);
        StatusResult result = orderService.confirmOrder(customer, newOrder, baseCartDto);
        if (result == StatusResult.ORDER_FIND_MISSING_PRODUCTS){
            return "redirect:/cart";
        }
        cartService.clearCookieCart(request, response);
        return "orderSuccess";
    }


    //???
    /*
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
    */

}
