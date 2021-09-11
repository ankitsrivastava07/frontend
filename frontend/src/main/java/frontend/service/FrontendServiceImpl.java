package frontend.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.constant.ResponseConstant;
import frontend.response.AddToCartResponse;
import frontend.response.ResetPasswordResponse;
import io.github.resilience4j.circuitbreaker.internal.CircuitBreakerStateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import frontend.controller.ChangePasswordReqest;
import frontend.controller.ChangePasswordRequestDto;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;
import frontend.dto.AddToCartRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FrontendServiceImpl implements FrontendService {

	@Autowired
	private ApiGatewayRequestUri apiGatewayRequestUri;

	@Override
	public void setCookie(HttpServletRequest request, HttpServletResponse response, String token) {

		Cookie cookies[] = request.getCookies();

		if (!token.isEmpty() && cookies != null) {
			for (Cookie cookie : cookies)

				if (cookie.getName().equalsIgnoreCase("session_Token")) {
					cookie.setValue(token);
					cookie.setPath("/");
					response.addCookie(cookie);
					return;
				}
		}

		Cookie cookie = new Cookie("session_Token", token);
		response.addCookie(cookie);
	}

	public String getToken(HttpServletRequest request) {

		Cookie cookies[] = request.getCookies();

		String userName = null;

		if (cookies != null)

			for (Cookie cookie : cookies)

				if (cookie.getName().equalsIgnoreCase("session_Token"))
					return userName = cookie.getValue();

		return userName;
	}

	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "defaultFallbackMethodHandleRequest")
	public TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response) {
		String token = getToken(request);
		TokenStatus tokenStatus = null;
		if (Objects.nonNull(token) && !token.isEmpty()) {
			tokenStatus = apiGatewayRequestUri.isValidToken(token).getBody();
			if (tokenStatus.getIsAccessTokenNewCreated() && tokenStatus.isStatus())
				setCookie(request, response, tokenStatus.getAccessToken());
			return tokenStatus;
		}
		return tokenStatus;
	}

	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "invalidateFallbackMethod")
	public TokenStatus invalidateToken(HttpServletRequest request) {

		String token = getToken(request);

		TokenStatus tokenStatus = null;

		if (Objects.nonNull(token) && !token.isEmpty())

		tokenStatus = apiGatewayRequestUri.invalidateToken(token).getBody();

		CircuitBreakerStateMachine circuitBreaker= new CircuitBreakerStateMachine("cloud-gateway-spring");
		io.github.resilience4j.circuitbreaker.CircuitBreaker.State state=circuitBreaker.getState();

		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "changePasswordFallback")
	public ResponseConstant changePassword(ChangePasswordReqest request) {
		ResponseConstant responseConstant = apiGatewayRequestUri.changePassword(request).getBody();
		return responseConstant;
	}

	public ResponseConstant changePasswordFallback(ChangePasswordReqest request,Throwable exception){
		ResponseConstant responseConstant = new ResponseConstant();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Sorry Server is currently down.Please try again later");
		responseConstant.setHttpStatus(503);
		return responseConstant;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "removeTokensFallbackMethod")
	public TokenStatus removeAllTokens(TokenStatus tokenStatus) {
		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();
		dto.setUserId(tokenStatus.getUserId());
		dto.setToken(tokenStatus.getAccessToken());
		Map<String, String> map = new HashMap<>();
		TokenStatus tokenStatus1 = apiGatewayRequestUri.invalidateTokens(dto).getBody();
		return tokenStatus1;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "loginFallBackMethod")
	public LoginStatus createAuthenticationToken(UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> map = new HashMap<>();
		map.put("email", userCredential.getEmail());
		map.put("password", userCredential.getPassword());

		LoginStatus loginStatus = apiGatewayRequestUri.createAuthenticationToken(userCredential).getBody();

		if (loginStatus.isStatus())
			setCookie(request, response, loginStatus.getToken());
		loginStatus.setToken(null);
		return loginStatus;
	}

	public LoginStatus loginFallBackMethod(UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response, Throwable exception) {

		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setMessage("Sorry Server is currently down.Please try again later");
		return loginStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "registerFallBackMethod")
	public CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto, HttpServletRequest request,
			HttpServletResponse response) {
		createUserRequestDto.setIsBlocked(Boolean.FALSE);
		CreateUserResponseStatus createUserResponseStatus = apiGatewayRequestUri.register(createUserRequestDto)
				.getBody();

		CircuitBreakerStateMachine circuitBreaker= new CircuitBreakerStateMachine("cloud-gateway-spring");
		io.github.resilience4j.circuitbreaker.CircuitBreaker.State state=circuitBreaker.getState();

		if (createUserResponseStatus.isStatus())
			setCookie(request, response, createUserResponseStatus.getToken());
		return createUserResponseStatus;
	}

	public CreateUserResponseStatus registerFallBackMethod(Throwable exception) {
		CreateUserResponseStatus createUserResponseStatus = new CreateUserResponseStatus();
		createUserResponseStatus.setMessage("Sorry Server is currently down.Please try again later");
		createUserResponseStatus.setHttpStatus(503);
		return createUserResponseStatus;
	}

	public TokenStatus defaultFallbackMethodHandleRequest(HttpServletRequest request, HttpServletResponse response,
			Throwable exception) {

		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

	public TokenStatus defaultFallbackMethodHandleRequest(ChangePasswordReqest request, Throwable exception) {

		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

	public TokenStatus removeTokensFallbackMethod(TokenStatus tokenStatus, Throwable exception) {
		TokenStatus tokenStatus2 = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

	public TokenStatus invalidateFallbackMethod(HttpServletRequest request, Throwable exception) {
		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "addToCartFallbackMethod")
	public AddToCartResponse addToCart(AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response) {
		AddToCartResponse addToCartResponse = apiGatewayRequestUri.addToCart(addToCartRequest).getBody();
		if(addToCartResponse.getIsAccessTokenNewCreated()) {
			setCookie(request,response,addToCartResponse.getSessionToken());
		}
		return addToCartResponse;
	}

	
	public AddToCartResponse addToCartFallbackMethod(AddToCartRequest addToCartRequest,HttpServletRequest request, HttpServletResponse response,Throwable exception) {
		
		AddToCartResponse addToCartResponse = new AddToCartResponse();
		addToCartResponse.setStatus(Boolean.FALSE);
		addToCartResponse.setSessionToken(null);
		addToCartResponse.setIsAccessTokenNewCreated(Boolean.FALSE);
		addToCartResponse.setCreatedAt(LocalDateTime.now());
		addToCartResponse.setMessage("Sorry Server is currently down.Please try again later");
		return addToCartResponse;
	}
	
	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "addToCartProductCountFallbackMethod")
	public AddToCartCountProductsResponse addToCartProductCount(String token, HttpServletRequest request, HttpServletResponse response) {

		AddToCartCountProductsResponse addToCartResponse = apiGatewayRequestUri.addToCartProductCount(token).getBody();
		if(addToCartResponse.getIsAccessTokenNewCreated()) {
			setCookie(request,response,addToCartResponse.getSessionToken());
		}
		return addToCartResponse;
	}

  public AddToCartCountProductsResponse addToCartProductCountFallbackMethod(String token,HttpServletRequest request, HttpServletResponse response,Throwable exception) {

	  AddToCartCountProductsResponse addToCartResponse = new AddToCartCountProductsResponse();
		addToCartResponse.setStatus(Boolean.FALSE);
		addToCartResponse.setSessionToken(null);
		addToCartResponse.setIsAccessTokenNewCreated(Boolean.FALSE);
		//addToCartResponse.setCreatedAt(LocalDateTime.now());
		addToCartResponse.setMessage("Sorry Server is currently down.Please try again later");
		return addToCartResponse;
	}
	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "userNameCheckFallback")
	public ResetPasswordResponse userNameCheck(String email){
		ResetPasswordResponse resetPasswordResponse = apiGatewayRequestUri.userNameCheck(email).getBody();
		return resetPasswordResponse;
	}
	public ResetPasswordResponse userNameCheckFallback(String email,Throwable exception){
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		resetPasswordResponse.setStatus(Boolean.FALSE);
		resetPasswordResponse.setMessage("Sorry Server is currently down.Please try again later");
		resetPasswordResponse.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		return resetPasswordResponse;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "authenticateIdentityTokenFallback")
	public ResponseConstant authenticateIdentityToken(String code) {
		ResponseConstant responseConstant = apiGatewayRequestUri.authenticateIdentityToken(code).getBody();
		return responseConstant;
	}

	public ResponseConstant authenticateIdentityTokenFallback(String email,Throwable exception){
		ResponseConstant responseConstant = new ResponseConstant();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Sorry Server is currently down.Please try again later");
		responseConstant.setHttpStatus(503);
		return responseConstant;
	}

}

