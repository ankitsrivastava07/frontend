package frontend.api.error;
import com.fasterxml.jackson.annotation.JsonProperty;
import frontend.validation.ValidationError;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ApiError {

	private Date timestamp;
	private Integer httpStatus;
	private String message;
	private String error;
	private Boolean status=Boolean.FALSE;
	private Boolean isSessionExpired;
	List<ValidationError> errors;
	@JsonProperty
	private String path;
	private boolean validFile=Boolean.FALSE;

	public ApiError(Integer httpStatus, String message, List<ValidationError> errors, Boolean status) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors=errors;
		this.status=status;
	}

	public ApiError(Date timestamp, Integer status, String message,String path) {
		this.timestamp = timestamp;
		this.httpStatus = status;
		this.message = message;
		this.path = path;
	}
}