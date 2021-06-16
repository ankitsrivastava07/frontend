package frontend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import frontend.controller.ChangePasswordRequestDto;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class FrontendServiceImpl implements FrontendService {

	private static final String BACKEND = null;

	@Autowired
	private ApiGatewayRequestUri apiGatewayRequestUri;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void setCookie(HttpServletRequest request, HttpServletResponse response, String token) {

		Cookie cookies[] = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies)

				if (cookie.getName().equalsIgnoreCase("session_Token")) {

					cookie.setValue(token);
					cookie.setPath("/");
					cookie.setMaxAge(60 * 30);
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

	@CircuitBreaker(name = "jwt-session", fallbackMethod = "defaultFallbackMethodHandleRequest")
	public TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response) {

		String token = getToken(request);

		TokenStatus tokenStatus = null;

		if (Objects.nonNull(token) && !token.isEmpty()) {

			tokenStatus = restTemplate.postForEntity(GatewayConstantURI.VALIDATE_TOKEN, token, TokenStatus.class)
					.getBody();

			// tokenStatus = apiGatewayRequestUri.isValidToken(token).getBody();

			if (tokenStatus.isStatus())
				setCookie(request, response, tokenStatus.getAccessToken());

			return tokenStatus;
		}

		return tokenStatus;
	}

	public void invalidateToken(HttpServletRequest request) {

		String token = getToken(request);

		TokenStatus tokenStatus = null;

		if (Objects.nonNull(token) && !token.isEmpty())

			tokenStatus = restTemplate.postForEntity(GatewayConstantURI.INVALIDATE_TOKEN, token, TokenStatus.class)
					.getBody();

		// tokenStatus = apiGatewayRequestUri.invalidateToken(token).getBody();

	}

	@Override
	public TokenStatus changePassword(String password, String token) {

		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();

		Map<String, String> map = new HashMap<>();

		map.put("password", password);

		map.put("token", token);

		map.put("request", "change-password");

		dto.setToken(map);

		TokenStatus tokenStatus = restTemplate
				.postForEntity(GatewayConstantURI.CHANGEPASSWORDREQUEST, token, TokenStatus.class).getBody();

		// TokenStatus tokenStatus = apiGatewayRequestUri.changePassword(dto).getBody();

		return tokenStatus;
	}

	@Override
	public TokenStatus removeAllTokens(HttpServletRequest request) {

		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();

		String token = getToken(request);

		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("request", "singout-from-alldevices");

		dto.setToken(map);

		TokenStatus tokenStatus = restTemplate
				.postForEntity(GatewayConstantURI.INVALIDATE_TOKENS, token, TokenStatus.class).getBody();
		// TokenStatus tokenStatus =
		// apiGatewayRequestUri.invalidateTokens(dto).getBody();

		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "user-service", fallbackMethod = "loginFallBackMethod")
	@RateLimiter(name="100")
	public LoginStatus createAuthenticationToken(UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> map = new HashMap<>();
		map.put("email", userCredential.getEmail());
		map.put("password", userCredential.getPassword());

		// LoginStatus loginStatus = restTemplate.postForObject(GatewayConstantURI.AUTHENTICATE, map, LoginStatus.class);

		 LoginStatus loginStatus = apiGatewayRequestUri.createAuthenticationToken(userCredential).getBody();

		if (loginStatus.isStatus())
			setCookie(request, response, loginStatus.getToken());
		return loginStatus;
	}

	public LoginStatus loginFallBackMethod(Throwable exception) {

		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setMessage("Sorry Server is currently down.Please try again later");
		return loginStatus;
	}

	public LoginStatus loginFallBackMethodTimeoutException(Throwable exception) {

		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setMessage("Sorry Server is taking too long to response.Please try again later");
		return loginStatus;
	}
	
	@Override
	@RateLimiter(name = "50", fallbackMethod = "registerFallBackMethod")
	@TimeLimiter(name = "50000")
	@CircuitBreaker(name = "user-service", fallbackMethod = "registerFallBackMethod")
	public CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto, HttpServletRequest request,
			HttpServletResponse response) {

		CreateUserResponseStatus status = restTemplate
				.postForEntity(GatewayConstantURI.CREATE_REQUEST, createUserRequestDto, CreateUserResponseStatus.class)
				.getBody();

		// CreateUserResponseStatus status =
		// apiGatewayRequestUri.register(createUserRequestDto).getBody();
		setCookie(request, response, status.getToken());
		return status;
	}

	public CreateUserResponseStatus registerFallBackMethod(Throwable exception) {
		CreateUserResponseStatus createUserResponseStatus = new CreateUserResponseStatus();
		createUserResponseStatus.setMessage("Sorry Server is currently down.Please try again later");
		createUserResponseStatus.setHttpStatus(503);
		return createUserResponseStatus;
	}

	public TokenStatus defaultFallbackMethodHandleRequest(Throwable exception) {

		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

}
