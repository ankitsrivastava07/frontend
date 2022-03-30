package frontend.api.response;

import frontend.validation.ValidationError;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
@NoArgsConstructor
@Data
public class CreateUserResponseStatus implements Serializable {

	private boolean status;
	private String token;
	private String browser;
	private String message;
	private Date createdAt;
	private Map<String, String> errorMessage;
	private List<ValidationError> errors;
	private Integer httpStatus= HttpStatus.OK.value();
	private Boolean isUserServiceAvaliable=Boolean.FALSE;
	private Boolean validationFailed=Boolean.FALSE;
}