package frontend.exceptionHandle;

import frontend.api.error.ApiError;
import frontend.api.response.AbstractResponse;
import frontend.constant.ConstantResponse;
import frontend.validation.ValidationError;
import frontend.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandle {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private ValidationUtil validationUtil;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleBadRequest(MissingServletRequestParameterException exception){
        Logger logger = LoggerFactory.getLogger(MissingServletRequestParameterException.class);
        logger.error("invalid request parameter is missing "+exception.getParameterName()+" "+exception.getParameterType() +" "+exception.getMessage()+" request uri "+request.getRequestURI());
        ModelAndView mv= new ModelAndView();
        mv.setViewName("error/400-error");
        return mv;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ValidationError> list= validationUtil.getAllErrors(result.getFieldErrors());
        return new ResponseEntity<>(new AbstractResponse(ConstantResponse.STATUS, HttpStatus.BAD_REQUEST,validationUtil.getAllErrors(result.getFieldErrors()),ConstantResponse.BAD_REQUEST, LocalDateTime.now(),ConstantResponse.VALIDATION_FAILED),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandle(Exception exception, WebRequest webRequest) {
        ApiError apiError = new ApiError(new Date(),Integer.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
