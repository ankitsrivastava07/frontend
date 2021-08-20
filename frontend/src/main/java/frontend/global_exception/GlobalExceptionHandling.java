package frontend.global_exception;

import frontend.api.error.ValidationError;
import frontend.constant.ConstantResponse;
import frontend.controller.AbstractResponse;
import frontend.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandling {

    @Autowired
    private ValidationUtil validationUtil;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ValidationError> list= validationUtil.getAllErrors(result.getFieldErrors());
      return new ResponseEntity<>(new AbstractResponse(ConstantResponse.STATUS,HttpStatus.BAD_REQUEST,validationUtil.getAllErrors(result.getFieldErrors()),ConstantResponse.BAD_REQUEST,LocalDateTime.now(),ConstantResponse.VALIDATION_FAILED),HttpStatus.BAD_REQUEST);

    }

}
