package frontend.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCredentialRequest {

	@NotNull(message = "Please enter valid email/username")
	@NotEmpty(message = "Please enter email/username")
	@Size(min = 3, max = 100, message = "Please enter username/email atleast 3 characters long")
	private String email;
	@NotBlank(message = "Please enter valid password")
	private String password;

}
