package frontend.constant;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ResponseConstant {
    private Boolean status=Boolean.FALSE;
    private String message;
    private Date date;
    private String accessToken;
    private String browser;
    private Integer httpStatus=200;
    public static final String FILE_EXTENSION_VALIDATION_FAILED_DEFAULT_MESSAGE="Invalid file format given, allow formats jpg|png|jpeg|gif";
}
