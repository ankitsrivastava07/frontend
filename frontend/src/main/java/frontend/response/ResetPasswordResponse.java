package frontend.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResetPasswordResponse {
    private Boolean status;
    private String message;
    private Boolean isMailServiceDown=Boolean.FALSE;
    private Integer httpStatus=HttpStatus.OK.value();
}
