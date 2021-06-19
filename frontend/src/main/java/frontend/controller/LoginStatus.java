package frontend.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class LoginStatus {

	private boolean status;

	private String message;
	@JsonIgnore
	@JsonProperty(value = "token")
	private String token;
}
