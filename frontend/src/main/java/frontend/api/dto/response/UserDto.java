package frontend.api.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserDto {
    @Pattern(regexp=".+@.+\\.[a-z]+",message="Please enter valid email")
    private String email;
    @NotBlank
    @Pattern(regexp="^(\\d*)$",message = "Please enter valid mobile number")
    private String mobile;
    //@Pattern(regexp="^(\\d)$",message="Please enter alternate mobile number")
    private String alternateMobile;
    @NotBlank(message="Please enter valid first name")
    private String firstName;
    @NotBlank(message = "Please enter valid last name")
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

