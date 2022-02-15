package frontend.constant;
import lombok.Data;
import java.util.Date;
@Data
public class ResponseConstant {
    private Boolean status=Boolean.FALSE;
    private String message;
    private Date date;
    private String accessToken;
    private String browser;
    private Integer httpStatus=200;
    public static final String UNAUTHORIZE_REQUEST_DEAULT_MESSAGE="Un Authorize Request";
    public static final String FILE_EXTENSION_VALIDATION_FAILED_DEFAULT_MESSAGE="Invalid file format given, allow formats jpg , png , jpeg , gif";
    public static final String FILE_LIMIT_EXCEED_DEAULT_MESSAGE="File size should be less than 10 MB";
    public static final String SESSION_EXPIRED_DEFAULT_MESSAGE="Your session has been expired.Please logined again";
}
