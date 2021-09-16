package frontend.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationUtil {

    public List<ValidationError> getAllErrors(List<FieldError> errors){
        List<ValidationError> list= new ArrayList<>();

        for(FieldError error : errors){

            ValidationError validationError = new ValidationError();
            validationError.setFieldName(error.getField());
            validationError.setMessage(error.getDefaultMessage());
            validationError.setRejectedValue(error.getRejectedValue());
            list.add(validationError);
        }
        return  list;
    }
}
