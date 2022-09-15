package frontend.api.dto.response;
import frontend.api.error.ApiError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private String email;
    @NotNull(message = "Please enter valid mobile")
    private String mobile;
    @Size(max = 10)
    private String alternateMobile;
    @NotBlank(message = "Please enter valid first name")
    private String firstName;
    @NotBlank(message = "Please enter valid last name")
    private String lastName;
    private String address;
    private String gender;
    private Date createdAt;
    private Integer httpStatus= HttpStatus.OK.value();
    private Boolean status;
    private Boolean alternateMobileAlreadyExist=Boolean.FALSE;
    private Boolean emailAlreadyExist=Boolean.FALSE;
    private String message;
    private Boolean accessTokenNew=Boolean.FALSE;
    private Boolean refreshTokenExpired=Boolean.FALSE;
    private String fileName;
    private String path;
    private Short fileSize;
    private String contentType;
    private byte contents[];
    private String s3BucketFileURL;
    private Long userId;
    private String s3BucketFileName;
    private String bucketName;
    private Boolean isSessionExpired;
    private ApiError apiError;
}

