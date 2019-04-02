package dbservice.controller;

import dbservice.dto.CartDto;
import dbservice.dto.ProductDto;
import dbservice.service.CartService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping( value = "/cart", method = RequestMethod.GET)
    public String toCart(ModelMap model, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        if (principalUser != null){
            List<CartDto> cartItems = cartService.getCartItemsByCustomersEmail(principalUser.getName());

            model.addAttribute("missingProducts", cartService.checkMissingItems(cartItems));
            model.addAttribute("cartItems", cartItems);
            int amount = 0;
            double total = 0;
            for(CartDto cart: cartItems){
                amount += cart.getQuantity();
                total += cart.getProduct().getPrice() * cart.getQuantity();
            }
            model.addAttribute("amount", amount);
            model.addAttribute("total", total);
            return "cart";
        }

        return "cart";
    }

    @ResponseBody
    @RequestMapping( value = "/addRemoveProduct",  method = RequestMethod.POST)
    public Map<String, String> addRemoveProduct(@RequestBody String id_product, HttpServletRequest request){
        Map<String, String> result = new HashMap<>();
        Principal principalUser = request.getUserPrincipal();

        if (principalUser != null){
            // проверка id на null
            String message = cartService.addRemoveCartItem(principalUser.getName(),
                    Long.parseLong(id_product));
            result.put("message", message);
            return result;
            //return cartService.addRemoveCartItem(principalUser.getName(),
                    //Long.parseLong(id_product));
        }
        result.put("message", "hello!!!");
        return result;
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
}

