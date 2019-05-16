package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import store.dto.CustomerDto;
import store.service.CustomerService;
import store.utils.StatusResult;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class LoginController {

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

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
