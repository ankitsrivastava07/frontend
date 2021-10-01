package frontend.api.request;

import lombok.Data;

@Data
public class ChangePasswordReqest {

	private String password;
	private String confirmPassword;
	private String code;
	private Boolean isPasswordChangeFromCodeIdentity;
	private String token;
	private Long userId;
	private String osName;
	private String locationName;
	private String browserName;

}
