package frontend.api.dto.response;

import frontend.validation.ValidationError;
import lombok.Data;
import java.util.List;

@Data
public class ApiResponse {

    private String message;
    private Boolean status;
    private List<ValidationError> errors;
}
