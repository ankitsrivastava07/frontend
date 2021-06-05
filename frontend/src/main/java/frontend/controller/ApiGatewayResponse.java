package frontend.controller;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiGatewayResponse {

	private boolean status;

	private String message;

	private LocalDateTime createdAt;

	private String token;

	private String firstName;

}
