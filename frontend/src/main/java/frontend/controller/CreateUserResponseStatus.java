package frontend.controller;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class CreateUserResponseStatus {

	private boolean status;

	private String message;

	private LocalDateTime createdAt;

	private Map<String, String> errorMessage;

}