package frontend.constant;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseConstant {
    private Boolean status;
    private String message;
    private Integer httpStatus=200;
}
