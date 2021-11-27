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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserCredentialRequest implements Authentication, Serializable {
	@NotBlank(message = "Please enter email/mobile")
	private String emailOrMobile;
	@NotBlank(message = "Please enter password")
	private String password;
	private boolean authenticated = false;
	private String browser;
	private String token;
	private String message;
	private Integer httpStatus;

	public UserCredentialRequest(){}

	public UserCredentialRequest(String token, String message,Integer httpStatus,String browser){
		this.token=token;
		this.message=message;
		this.httpStatus=httpStatus;
		this.browser=browser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return password;
	}

	@Override
	public Object getDetails() {
		return emailOrMobile;
	}

	@Override
	public Object getPrincipal() {
		return emailOrMobile;
	}

	@Override
	public String getName() {
		return emailOrMobile;
	}
}
