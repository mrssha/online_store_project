package dbservice.controller;


import dbservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping( value = "/profile",  method = RequestMethod.GET)
    public String customerProfile(){ ;
        return "profile";
    }

}
