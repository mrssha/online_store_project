package store.security;

import store.utils.LogMessage;
import store.service.CartService;
import store.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {


    private CustomerService customerService;
    private CartService cartService;

    private static final Logger logger = Logger.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        String email = auth.getName();
        HttpSession session = request.getSession();
        session.setAttribute("principalUser", customerService.getCustomerByEmail(email));
        Cookie cookieCart = WebUtils.getCookie(request, "productCart");
        cartService.mergeCarts(response, email, cookieCart);
        response.sendRedirect(request.getContextPath() + "/");
        logger.info(String.format(LogMessage.LOGIN_SUCCESS, email));
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
