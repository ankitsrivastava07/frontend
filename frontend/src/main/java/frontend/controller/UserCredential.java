package frontend.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCredential {

	@NotNull(message = "Please enter valid email/username")
	@NotEmpty(message = "Please enter email/username")
	@Size(min = 3, max = 100, message = "Please enter username/email atleast 3 characters long")
	private String email;

	@NotNull(message = "Please enter valid password")
	@Size(min = 6, max = 100, message = "Please enter password atleast 6 characters long")
	@NotEmpty(message = "Please enter valid password")
	private String password;

}
