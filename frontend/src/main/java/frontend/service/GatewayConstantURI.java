package frontend.service;

public class GatewayConstantURI {

	public static final String URI = "http://cloud-gateway-spring.herokuapp.com/";

	public static final String VALIDATE_TOKEN = "http://cloud-gateway-spring.herokuapp.com/token-session/validate-token";
	
	public static final String AUTHENTICATE = "http://localhost:8765/users/login";
	
	public static final String CREATE_REQUEST = "http://cloud-gateway-spring.herokuapp.com/users/register";
	
	public static final String INVALIDATE_TOKEN = "http://cloud-gateway-spring.herokuapp.com/token-session/invalidate-token";
	
	public static final String CHANGEPASSWORDREQUEST = "http://cloud-gateway-spring.herokuapp.com/users/change-password";
	
	public static final String INVALIDATE_TOKENS = "http://cloud-gateway-spring.herokuapp.com/token-session/invalidate-tokens";
}
