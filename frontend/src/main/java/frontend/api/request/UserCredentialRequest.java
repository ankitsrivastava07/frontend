package frontend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserCredentialRequest implements Authentication {

	@NotBlank(message = "Please enter email/mobile")
	private String emailOrMobile;
	@NotBlank(message = "Please enter password")
	private String password;
	private boolean authenticated = false;
	private String browser;
	private String token;
	private String title;
	private String message;
	private Integer httpStatus;

	public UserCredentialRequest(){}

	public UserCredentialRequest(String token, String message,Integer httpStatus,String browser, String title){
		this.token=token;
		this.message=message;
		this.httpStatus=httpStatus;
		this.browser=browser;
		this.title=title;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}
