package dbservice.controller;

import dbservice.dto.CustomerDto;
import dbservice.service.CartService;
import dbservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;


    @Autowired
    @Qualifier("LocalValidatorFactoryBean")
    private Validator validator;



    @RequestMapping( value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap modelMap){
        modelMap.addAttribute("newUser", new CustomerDto());
        return "signup";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //binder.setValidator(validator);
    }

    @RequestMapping( value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid @ModelAttribute("newUser") CustomerDto customerDto,
                               BindingResult bindResult){

        ModelAndView modelAndView = new ModelAndView();
        if (bindResult.hasErrors()) {
            modelAndView.setViewName("signup");
            return modelAndView;
        }

        String result = customerService.addCustomer(customerDto);
        if (result.equals("EMAIL_EXIST")){
            String message = "User with such email already exists. " +
                    "Please, enter another email or go to the login page.";
            modelAndView.addObject("message", message);
            modelAndView.setViewName("signup");
            return modelAndView;
        }
        String message = "Registration completed successfully. Please, enter your login details.";
        modelAndView.addObject("message_user_created", message);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping( value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


//    @RequestMapping( value = "/login", method = RequestMethod.POST)
//    public String login(HttpServletRequest request){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = userDetails.getUsername();
//        HttpSession session = request.getSession();
//        session.setAttribute("principalUser", customerService.getCustomerByEmail(email));
//        return "redirect:/";
//    }


//    @RequestMapping( value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request, Authentication authentication){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = userDetails.getUsername();
//        HttpSession session = request.getSession();
//        session.setAttribute("principalUser",null);
//        return "redirect:/login?logout";
//    }


}
