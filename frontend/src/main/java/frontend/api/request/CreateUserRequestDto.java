package frontend.api.request;

import lombok.Data;

@Data
public class CreateUserRequestDto {

	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private Boolean isBlocked;
	private String password;

}
