package frontend.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateUserRequestDto implements Serializable {

	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private Boolean isBlocked=Boolean.FALSE;
	private String password;
}
