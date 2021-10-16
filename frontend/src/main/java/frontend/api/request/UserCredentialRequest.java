package frontend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCredentialRequest {
	@NotBlank(message = "Please enter email/mobile")
	private String emailOrMobile;
	@NotBlank(message = "Please enter password")
	private String password;

}
