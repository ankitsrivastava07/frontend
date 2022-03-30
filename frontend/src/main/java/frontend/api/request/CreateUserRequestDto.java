package frontend.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreateUserRequestDto implements Serializable {
    @NotBlank(message="Please enter valid first name")
	private String firstName;
	@NotBlank(message="Please enter valid last name")
	private String lastName;
	@NotBlank(message="Please enter valid mobile number")
	private String mobile;
	//@Pattern(regexp = ".+@.+\\..+",message = "Please enter valid email id")
	private String email;
	//@NotBlank(message = "Please enter valid is Blocked value")
	private Boolean isBlocked=Boolean.FALSE;
	@NotBlank(message = "Please enter valid password")
	@Size(min=6,max = 30,message = "Password minimum 6 characters and maximum 30 characters long")
	private String password;
	@Size(min=6, max=30, message = "Confirm Password minimum 6 characters and maximum 30 characters long")
	private String confirmPassword;
	@NotBlank(message = "Please enter browser name")
	private String browserName;
	private String browser;
	@NotBlank(message = "Please enter os name")
	private String osname;
}
