package frontend.api.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String mobile;
    private String alternateMobile;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String browser;
    private Date createdAt;
    private Integer httpStatus= HttpStatus.OK.value();
    private Boolean status;
    private Boolean alternateMobileAlreadyExist=Boolean.FALSE;
    private Boolean emailAlreadyExist=Boolean.FALSE;
    private String message;
    private String accessToken;
    private Boolean accessTokenNew=Boolean.FALSE;
    private Boolean refreshTokenExpired=Boolean.FALSE;

}

