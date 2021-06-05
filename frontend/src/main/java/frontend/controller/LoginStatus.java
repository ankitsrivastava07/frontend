package frontend.controller;

import lombok.Data;

@Data
public class LoginStatus {

	private boolean status;

	private String message;

	private String token;

	private String firstName;
}
