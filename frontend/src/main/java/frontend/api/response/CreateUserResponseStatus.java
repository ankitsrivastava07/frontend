package frontend.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
@NoArgsConstructor
@Data
public class CreateUserResponseStatus implements Serializable {

	private boolean status;

	private String token;
	
	private String message;

	private Date createdAt;

	private Map<String, String> errorMessage;

	private Integer httpStatus;
	private Boolean isUserServiceAvaliable=Boolean.FALSE;
}