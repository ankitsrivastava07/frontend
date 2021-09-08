package frontend.controller;

import lombok.Data;

@Data
public class ChangePasswordReqest {

	private String password;
	private String code;
	private Boolean isPasswordChangeFromCodeIdentity;
	private String token;
	private Long userId;
}
