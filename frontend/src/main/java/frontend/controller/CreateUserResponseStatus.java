package frontend.controller;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

@Data
public class CreateUserResponseStatus {

	private boolean status;

	private String token;
	
	private String message;

	private LocalDateTime createdAt;

	private Map<String, String> errorMessage;

	private Integer httpStatus;
}