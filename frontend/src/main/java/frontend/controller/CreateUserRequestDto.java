package frontend.controller;

import lombok.Data;

@Data
public class CreateUserRequestDto {

	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;

}
