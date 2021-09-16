package frontend.api.response;

import frontend.validation.ValidationError;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiResponse {

    private String message;
    private HttpStatus httpStatus;
    private Boolean status;
    private List<ValidationError> errors;
    private LocalDateTime timestamp;
    private Boolean validationFailed=Boolean.FALSE;
    public ApiResponse() {
    }

    public ApiResponse(Boolean status, HttpStatus httpStatus, String message, List<ValidationError> errors, LocalDateTime timestamp, Boolean validation) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = status;
        this.errors = errors;
        this.timestamp = timestamp;
        this.validationFailed=validation;
    }

    public ApiResponse(Boolean status, HttpStatus httpStatus, String message, List<ValidationError> errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = status;
        this.errors = errors;
    }

    public ApiResponse(Boolean status, HttpStatus httpStatus, String message, LocalDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = status;
        this.timestamp = timestamp;
    }

    public ApiResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(Boolean status, List<ValidationError> errors, String message) {
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

}
