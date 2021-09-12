package frontend.constant;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ResponseConstant {
    private Boolean status;
    private String message;
    private Date date;
    private String accessToken;
    private Integer httpStatus=200;
}
