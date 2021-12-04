package frontend.api.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ApiError {

	private Date timestamp;
	private Integer status;
	private String message;
	@JsonProperty
	private String path;
	private boolean validFile=Boolean.FALSE;

	public ApiError(Date timestamp, Integer status, String message,String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.path = path;
	}
}