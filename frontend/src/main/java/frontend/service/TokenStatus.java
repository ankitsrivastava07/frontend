package frontend.service;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class TokenStatus {

	private boolean status;

	private boolean logined;
	private Boolean isAccessTokenNewCreated;
	private String message;
	private Long userId;

	private Date createdAt;
	private String expireAt;
	private String accessToken;
	private String firstName;
}
