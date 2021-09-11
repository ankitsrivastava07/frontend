package frontend.controller;

import java.util.Map;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
	private Long userId;
	private String password;
	private String token;
}
