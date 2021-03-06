package store.handler;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;


@ControllerAdvice
public class ControllerExceptionHandler {

    private static Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(UnsupportedEncodingException.class)
    protected void handleEncodingExc(Exception e) {
        logger.error("Encoding error: ", e);
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Encoding error", e);
    }

    @ExceptionHandler(HibernateException.class)
    public void handleHibernateExc(Exception e) {
        logger.error("Base query exception: ", e);
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Base query exception", e);
    }

    @ExceptionHandler(RequestRejectedException.class)
    public void handleRejectedExc(Exception e) {
        logger.error("Rejected error: ", e);
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Rejected error", e);
    }

    @ExceptionHandler(Exception.class)
    public void handleExc(Exception e) {
        logger.error("Unknown error: ", e);
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", e);
    }
}
