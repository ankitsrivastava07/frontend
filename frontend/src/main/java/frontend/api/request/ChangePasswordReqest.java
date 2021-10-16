package frontend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import frontend.validation.ValidPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
public class ChangePasswordReqest {

	@NotBlank(message = "password is mandatory")
	@Size(min=5,max=30,message = "Password minimum 5 characters and maximum 30 characters long")
	@ValidPassword
	@JsonProperty(value="password")
	private String password;
	@NotBlank(message = "Confirm password is mandatory")
	private String confirmPassword;
	@ValidPassword
	@NotBlank(message = "code token can't be empty")
	private String code;
	private Boolean isPasswordChangeFromCodeIdentity;
	private String token;
	private Long userId;
	@NotBlank(message = "osName can't be empty")
	private String osName;
	@NotBlank(message = "locationName can't be empty")
	private String locationName;
	@NotBlank(message = "browserName system can't be empty")
	private String browserName;

}
