package store.controller;

import store.dto.CustomerDto;
import store.utils.StatusResult;
import store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping( value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap modelMap){
        modelMap.addAttribute("newUser", new CustomerDto());
        return "signup";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping( value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@Valid @ModelAttribute("newUser") CustomerDto customerDto,
                               BindingResult bindResult){
        ModelAndView modelAndView = new ModelAndView();
        if (bindResult.hasErrors()) {
            modelAndView.setViewName("signup");
            return modelAndView;
        }
        StatusResult result = customerService.addCustomer(customerDto);
        if (result == StatusResult.EMAIL_ALREADY_EXIST){
            modelAndView.addObject("message",result.getMessage());
            modelAndView.setViewName("signup");
            return modelAndView;
        }
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
