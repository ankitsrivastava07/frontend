package frontend.api.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
    private String fileName;
    private String path;
    private Short fileSize;
    private String contentType;
    private byte contents[];

}

