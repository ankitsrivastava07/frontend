package frontend.exceptionHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import frontend.api.error.ApiError;
import frontend.api.response.AbstractResponse;
import frontend.constant.ConstantResponse;
import frontend.constant.ResponseConstant;
import frontend.controller.LoginStatus;
import frontend.exceptionHandle.exception.InvalidCredentialException;
import frontend.service.TokenStatus;
import frontend.validation.ValidationError;
import frontend.validation.ValidationUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleBadRequest(HttpRequestMethodNotSupportedException exception){
        Logger logger = LoggerFactory.getLogger(MissingServletRequestParameterException.class);
        logger.error("invalid request parameter is missing "+exception.getMessage());
        ApiError apiError= new ApiError(new Date(),HttpStatus.METHOD_NOT_ALLOWED.value(),exception.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(apiError,HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ValidationError> list= validationUtil.getAllErrors(result.getFieldErrors());
        return new ResponseEntity<>(new AbstractResponse(ConstantResponse.STATUS, HttpStatus.BAD_REQUEST,validationUtil.getAllErrors(result.getFieldErrors()),ConstantResponse.BAD_REQUEST, LocalDateTime.now(),ConstantResponse.VALIDATION_FAILED),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> authenticationTokenMissing(MissingRequestHeaderException exception) {
        ApiError apiError = new ApiError(new Date(),Integer.valueOf(HttpStatus.UNAUTHORIZED.value()), "Missing authentication token",request.getRequestURI());
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView missingServletRequestParameter(MissingServletRequestParameterException exception) {
        return new ModelAndView("error/400-error");
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<?> apiError(InvalidHeaderException exception) {
        ApiError apiError = new ApiError(new Date(),Integer.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(com.fasterxml.jackson.databind.JsonMappingException.class)
    public ResponseEntity<?> exceptionHandle(com.fasterxml.jackson.databind.JsonMappingException exception) {
        ApiError apiError = new ApiError(new Date(),Integer.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid json value provided",request.getRequestURI());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> exception(HttpMessageNotReadableException exception) {
        ApiError apiError = new ApiError(new Date(),Integer.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid json request saint",request.getRequestURI());
        Throwable cause = exception.getCause();
        if(cause instanceof JsonMappingException){
            JsonMappingException jpe = (JsonMappingException) cause;
            String msg="Invalid request field : ";
            if (jpe.getPath() != null && jpe.getPath().size() > 0) {
                msg = msg+jpe.getPath().get(0).getFieldName();
            }
            ApiError mapping = new ApiError(new Date(),Integer.valueOf(HttpStatus.BAD_REQUEST.value()), msg,request.getRequestURI());
            return new ResponseEntity<>(mapping,HttpStatus.BAD_REQUEST);
        }
        if(cause instanceof JsonParseException){
            ApiError jsonParsngError = new ApiError(new Date(),Integer.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid json request provided",request.getRequestURI());
            return new ResponseEntity<>(jsonParsngError,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> tokenException(ExpiredJwtException exception) {
        Logger logger = LoggerFactory.getLogger(String.class);
        logger.info("access Token has been expired "+exception.getMessage());
        TokenStatus tokenStatus = new TokenStatus();
        tokenStatus.setStatus(Boolean.FALSE);
        tokenStatus.setAccessTokenExpired(Boolean.TRUE);
        tokenStatus.setMessage("Your session has been expired.Please login again");
        return new ResponseEntity<>(tokenStatus, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> tokenException(MaxUploadSizeExceededException exception) {
        ApiError apiError = new ApiError(new Date(),HttpStatus.BAD_REQUEST.value(), ResponseConstant.FILE_LIMIT_EXCEED_DEAULT_MESSAGE,request.getRequestURI());
        apiError.setValidFile(Boolean.FALSE);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<?> invalidCredentialException(InvalidCredentialException invalidCredentialsException){
        LoginStatus loginStatus = new LoginStatus();
        loginStatus.setStatus(Boolean.FALSE);
        loginStatus.setMessage(invalidCredentialsException.getMessage());
        return new ResponseEntity<>(loginStatus,HttpStatus.OK);
    }

}
