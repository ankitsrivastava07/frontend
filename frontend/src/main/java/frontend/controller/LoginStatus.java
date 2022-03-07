package frontend.controller;
import lombok.Data;
import java.io.Serializable;

@Data
public class LoginStatus implements Serializable {

	private boolean status= Boolean.FALSE;
	private String message;
	private String token;
	private String title;
	private Integer httpStatus=200;
	private String browser;

	public LoginStatus(){}

	public LoginStatus(Boolean status,String message,String token,String browser,String title){
		this.status=status;
		this.message=message;
		this.token=token;
		this.browser=browser;
		this.httpStatus=httpStatus;
	}

}
