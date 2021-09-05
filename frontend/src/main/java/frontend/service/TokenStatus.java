package frontend.service;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TokenStatus {

	private boolean status;

	private boolean logined;
	private Boolean isAccessTokenNewCreated;
	private String message;
	
	private Long userId;

	private LocalDateTime createdAt;

	private String accessToken;

	private String firstName;
}
