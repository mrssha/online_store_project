package dbservice.controller;

import dbservice.dto.CartDto;
import dbservice.dto.CookieCartDto;
import dbservice.dto.CustomerDto;
import dbservice.service.CartService;
import dbservice.service.CustomerService;
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
import java.util.List;
import java.util.Map;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping( value = "/cart", method = RequestMethod.GET)
    public String toCart(@CookieValue(value = "productCart", required = false) Cookie cookieCart,
                         HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws UnsupportedEncodingException {
        //Principal principalUser = request.getUserPrincipal();
        CookieCartDto cartProductsCookie = cartService.getCartProductsCookie(cookieCart);

//        if (principalUser != null){
//            List<CartDto> cartItems = cartService.getCartItemsByCustomersEmail(principalUser.getName());
//            model.addAttribute("missingProducts", cartService.checkMissingItems(cartItems));
//        }else {
//            model.addAttribute("missingProducts",
//                    cartService.checkMissingItems(cartProductsCookie.getCookieCart()));
//        }
        model.addAttribute("missingProducts", cartService.checkMissingItems(cartProductsCookie.getCookieCart()));
        model.addAttribute("cartCookie", cartProductsCookie.getCookieCart());
        model.addAttribute("amount", cartProductsCookie.getAmountProducts());
        model.addAttribute("total", cartProductsCookie.getTotalPrice());
        return "cart";
    }


    @ResponseBody
    @RequestMapping( value = "/addToCart",  method = RequestMethod.POST)
    public Map<String, String> addToCart(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
             return cartService.addToCart(principalUser.getName(),
                    Long.parseLong(id_product));
        }
        return new HashMap<>();
    }

    @ResponseBody
    @RequestMapping( value = "/removeFromCart",  method = RequestMethod.POST)
    public Map<String, String> removeFromCart(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
            return cartService.removeFromCart(principalUser.getName(),
                    Long.parseLong(id_product));
        }
        return new HashMap<>();
    }

    @ResponseBody
    @RequestMapping( value = "/deleteCartItem", method = RequestMethod.POST)
    public Map<String, String> deleteCategory(@RequestBody String id_product, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customerDto = customerService.getCustomerByEmail(principalUser.getName());
        CartDto cartItem = cartService.getByProductAndCustomer(customerDto.getId(),
                Long.parseLong(id_product));
        if (cartItem!=null) {
            cartService.deleteById(cartItem.getId());
        }
        return new HashMap<>();
    }



    // старая версия
    /*
    @ResponseBody
    @RequestMapping( value = "/deleteCartItem", method = RequestMethod.POST)
    public void deleteCategory(@RequestBody String id_cartItem){
        cartService.deleteById(Long.parseLong(id_cartItem));
    }
    */


    // удалить если не нужен
    /*
    @ResponseBody
    @RequestMapping( value = "/addRemoveProduct",  method = RequestMethod.POST)
    public Map<String, String> addRemoveProduct(@RequestBody String id_product, HttpServletRequest request){
        Map<String, String> result = new HashMap<>();
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
            // проверка id на null
            String result = cartService.addRemoveCartItem(principalUser.getName(),
                    Long.parseLong(id_product));
            result.put("result", result);
            return result;
        }
        result.put("result", "");
        return result;
    }
    */
}

