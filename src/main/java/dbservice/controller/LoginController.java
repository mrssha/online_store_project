package dbservice.controller;

import dbservice.dto.CustomerDto;
import dbservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping( value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap modelMap){
        modelMap.addAttribute("newUser", new CustomerDto());
        return "signup";
    }


    @RequestMapping( value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@ModelAttribute("newUser") CustomerDto customerDto){
        ModelAndView modelAndView = new ModelAndView();
        String result = customerService.addCustomer(customerDto);
        if (result.equals("EMAIL_EXIST")){
            String message = "Пользователь с таким email уже существует." +
                    " Введите другой email либо перейдите на сраницу входа в систему";
            modelAndView.addObject("message", message);
            modelAndView.setViewName("signup");
            return modelAndView;
        }
        String message = "Регистрация прошла успешно. Введите данные для входа в систему";
        modelAndView.addObject("message_user_created", message);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping( value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping( value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println("User has authorities: " + userDetails.getAuthorities());
        String email = userDetails.getUsername();
        HttpSession session = request.getSession();
        session.setAttribute("principalUser", customerService.getCustomerByEmail(email));
//        session.setAttribute("cart", new HashMap<String, Integer>());
        return "home";
    }


    @RequestMapping( value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Authentication authentication){
        HttpSession session = request.getSession();
        session.setAttribute("principalUser",null);
        return "redirect:/login?logout";
    }


    @RequestMapping( value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap modelMap){
        return "admin";
    }

}
