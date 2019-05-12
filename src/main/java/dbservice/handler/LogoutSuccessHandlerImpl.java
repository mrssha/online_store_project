package dbservice.handler;


import dbservice.result.LogMessage;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private static final Logger logger = Logger.getLogger(LogoutSuccessHandlerImpl.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("principalUser",null);
        response.sendRedirect(request.getContextPath() + "/login?logout");
        logger.info(LogMessage.LOGOUT_SUCCESS);
    }
}
