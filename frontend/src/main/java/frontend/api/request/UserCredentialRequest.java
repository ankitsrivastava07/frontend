package frontend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserCredentialRequest {
	@NotBlank(message = "Please enter email/mobile")
	private String emailOrMobile;
	@NotBlank(message = "Please enter password")
	private String password;
	private boolean authenticated = false;
	public UserCredentialRequest(){}

}
