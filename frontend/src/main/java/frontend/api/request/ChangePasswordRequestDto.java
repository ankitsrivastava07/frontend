package frontend.api.request;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
	private Long userId;
	private String password;
	private String token;
}
