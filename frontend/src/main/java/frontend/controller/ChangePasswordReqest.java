package frontend.controller;

import lombok.Data;

@Data
public class ChangePasswordReqest {

	private String password;
	private String token;
	private Long userId;
}
