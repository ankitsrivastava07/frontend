package frontend.api.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class UserDto {
    private String email;
    private String mobile;
    private String password;
    private String alternateMobile;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date createdAt;
    private Integer httpStatus= HttpStatus.OK.value();
    private Boolean status;
    private Boolean alternateMobileAlreadyExist=Boolean.FALSE;
    private String message;
}

