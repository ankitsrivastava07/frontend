package frontend.api.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserDto {
    private String email;
    private String mobile;
    private String password;
    private String firstName;
    private String lastName;
    private Integer httpStatus= HttpStatus.OK.value();
    private Boolean status;
}
