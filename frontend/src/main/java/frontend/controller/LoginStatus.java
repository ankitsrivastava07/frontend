package frontend.controller;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Data
public class LoginStatus {

	private boolean status;
	private String message;
	private String token;
	private Integer httpStatus=200;
	private String browser;
}
