package frontend.api.error;

import lombok.Data;

import java.util.Date;

@Data
public class ApiError {

	private Date timestamp;
	private Integer status;
	private String error;
	private String path;

	public ApiError(Date timestamp, Integer status, String error,String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
	}
}