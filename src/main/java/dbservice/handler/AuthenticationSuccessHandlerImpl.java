package dbservice.handler;


import dbservice.service.CartService;
import dbservice.service.CustomerService;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        System.out.println("Login");
        String email = auth.getName();
        HttpSession session = request.getSession();
        session.setAttribute("principalUser", customerService.getCustomerByEmail(email));
        Cookie cookieCart = WebUtils.getCookie(request, "productCart");
        cartService.mergeCarts(response, email, cookieCart);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
