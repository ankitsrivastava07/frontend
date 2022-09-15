package frontend.api.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiGatewayResponse {

	private boolean status;

	private String message;

	private LocalDateTime createdAt;

	private String token;

	private String firstName;

}
