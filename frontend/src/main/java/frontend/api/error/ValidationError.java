package frontend.api.error;

import lombok.Data;

@Data
public class ValidationError {

    private String fieldName;
    private String message;
    private Object rejectedValue;
}
