package store.controller;

import store.dto.CartDto;
import store.dto.CookieCartDto;
import store.dto.CustomerDto;
import store.service.CartService;
import store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
public class CartController {

    private CartService cartService;
    private CustomerService customerService;

    @RequestMapping( value = "/cart", method = RequestMethod.GET)
    public String toCart(@CookieValue(value = "productCart", required = false) Cookie cookieCart,
                         HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws UnsupportedEncodingException {
        CookieCartDto cartProductsCookie = cartService.getCartProductsCookie(cookieCart);
        model.addAttribute("missingProducts", cartService.checkMissingItems(cartProductsCookie.getCookieCart()));
        model.addAttribute("cartCookie", cartProductsCookie.getCookieCart());
        model.addAttribute("amount", cartProductsCookie.getAmountProducts());
        model.addAttribute("total", cartProductsCookie.getTotalPrice());
        return "cart";
    }


    @ResponseBody
    @RequestMapping( value = "/addToCart",  method = RequestMethod.POST)
    public String addToCart(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
             return cartService.addToCart(principalUser.getName(),
                    Long.parseLong(id_product));
        }
        return "";
    }

    @ResponseBody
    @RequestMapping( value = "/removeFromCart",  method = RequestMethod.POST)
    public String removeFromCart(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
            return cartService.removeFromCart(principalUser.getName(),
                    Long.parseLong(id_product));
        }
        return "";
    }

    @ResponseBody
    @RequestMapping( value = "/deleteCartItem", method = RequestMethod.POST)
    public void deleteCartItem(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customerDto = customerService.getCustomerByEmail(principalUser.getName());
        CartDto cartItem = cartService.getByProductAndCustomer(customerDto.getId(),
                Long.parseLong(id_product));
        if (cartItem!=null) {
            cartService.deleteById(cartItem.getId());
        }
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}

