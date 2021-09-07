package frontend.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResetPasswordResponse {
    private Boolean status;
    private String message;
    private HttpStatus httpStatus=HttpStatus.OK;
}
