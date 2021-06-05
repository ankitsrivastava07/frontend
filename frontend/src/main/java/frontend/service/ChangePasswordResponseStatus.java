package frontend.service;

import lombok.Data;

@Data
public class ChangePasswordResponseStatus {

	private boolean status;

	private String message;

	private String token;
}