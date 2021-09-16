package frontend.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class UserNameExistRequest {
    @NotBlank(message = "Please enter valid email/mobile")
    @Size(min = 3, max = 100, message = "Please enter valid email/mobile atleast 3 and maximum 100 characters long")
    private String email;
}
