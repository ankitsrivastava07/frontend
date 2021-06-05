package frontend.controller;

import java.util.Map;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
	private Map<String, String> token;
}
