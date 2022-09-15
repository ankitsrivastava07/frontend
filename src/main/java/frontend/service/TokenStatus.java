package frontend.service;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class TokenStatus {

	private boolean status=Boolean.FALSE;
	private boolean logined=Boolean.FALSE;
	private Boolean isAccessTokenNewCreated;
	private String message;
	private Long userId;
	private Integer httpStatus=200;
	private Date createdAt;
	private String expireAt;
	private String accessToken;
	private String firstName;
	private String browser;
	private Boolean accessTokenExpired=Boolean.FALSE;
	private Boolean refreshTokenExpired=Boolean.FALSE;
}
