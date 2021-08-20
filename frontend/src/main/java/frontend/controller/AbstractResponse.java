package frontend.controller;

import frontend.api.error.ValidationError;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class AbstractResponse extends ApiResponse{

 public AbstractResponse(){}

    public AbstractResponse(Boolean status,String message){

     super(status,message);
    }

    public AbstractResponse(Boolean status, List<ValidationError> errors, String message) {
      super(status,errors,message);
    }

    public AbstractResponse(Boolean status, HttpStatus httpStatus, List<ValidationError> errors, String message){
     super(status,httpStatus,message,errors);
    }

    public AbstractResponse(Boolean status, HttpStatus httpStatus, String message,LocalDateTime timestamp){
        super(status,httpStatus,message,timestamp);
    }


    public AbstractResponse(Boolean status,HttpStatus httpStatus, List<ValidationError> errors,String message, LocalDateTime timestamp,Boolean validation) {
        super(status,httpStatus,message,errors,timestamp,validation);
    }
}
