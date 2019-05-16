package store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.dto.AddressDto;
import store.dto.BaseCartDto;
import store.dto.CustomerDto;
import store.dto.OrderDto;
import store.entity.AddressType;
import store.service.AddressService;
import store.service.CartService;
import store.service.CustomerService;
import store.service.OrderService;
import store.utils.StatusResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {


    private CartService cartService;
    private OrderService orderService;
    private AddressService addressService;
    private CustomerService customerService;


    @RequestMapping( value = "/order", method = RequestMethod.GET)
    public String createOrder(ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        BaseCartDto baseCartDto = cartService.getBaseCartByCustomersEmail(principalUser.getName());
        if (baseCartDto.getTotalSum()==0){
            return "redirect:/cart";
        }
        List<AddressDto> addresses = addressService.getAddressByCustomerId(customer.getId());
        List<AddressDto> pickupPoints = addressService.getByAddressType(AddressType.PICKUP);
        modelMap.addAttribute("addresses", addresses);
        modelMap.addAttribute("pickupPoints", pickupPoints);
        modelMap.addAttribute("cart", baseCartDto);
        modelMap.addAttribute("amount", baseCartDto.getAmountProducts());
        modelMap.addAttribute("total", baseCartDto.getTotalSum());
        modelMap.addAttribute("newAddress", new AddressDto());
        return "order";
    }

    @RequestMapping( value = "/order", method = RequestMethod.POST)
    public String addAddressToOrder(@Valid @ModelAttribute("newAddress") AddressDto newAddress,
                                    BindingResult bindResult,
                                    ModelMap modelMap, HttpServletRequest request){
        Principal principalUser = request.getUserPrincipal();
        CustomerDto customer = customerService.getCustomerByEmail(principalUser.getName());
        if (bindResult.hasErrors()) {
            modelMap.addAttribute("addresses",
                    addressService.getAddressByCustomerId(customer.getId()));
            modelMap.addAttribute("pickupPoints", addressService.getByAddressType(AddressType.PICKUP));
            BaseCartDto baseCartDto = cartService.getBaseCartByCustomersEmail(principalUser.getName());
            modelMap.addAttribute("amount", baseCartDto.getAmountProducts());
            modelMap.addAttribute("total", baseCartDto.getTotalSum());
            return "order";
        }
        newAddress.setCustomer(customer);
        addressService.addAddress(newAddress);
        return "redirect:/order";
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

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
