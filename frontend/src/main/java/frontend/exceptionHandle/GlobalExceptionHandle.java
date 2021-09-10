package frontend.exceptionHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandle {
    @Autowired
    HttpServletRequest request;
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleBadRequest(MissingServletRequestParameterException exception){
        Logger logger = LoggerFactory.getLogger(MissingServletRequestParameterException.class);
        logger.error("invalid request parameter is missing "+exception.getParameterName()+" "+exception.getParameterType() +" "+exception.getMessage()+" request uri "+request.getRequestURI());
        ModelAndView mv= new ModelAndView();
        mv.setViewName("400-error");
        return mv;
    }
}
