package frontend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frontend.controller.ChangePasswordReqest;
import frontend.controller.ChangePasswordRequestDto;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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

			if (tokenStatus.isStatus())
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

		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "defaultFallbackMethodHandleRequest")
	public TokenStatus changePassword(ChangePasswordReqest request) {
		TokenStatus tokenStatus = apiGatewayRequestUri.changePassword(request).getBody();

		tokenStatus.setAccessToken(null);
		tokenStatus.setFirstName(null);
		tokenStatus.setUserId(null);
		tokenStatus.setLogined(true);
		tokenStatus.setCreatedAt(null);
		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "defaultFallbackMethodHandleRequest")
	public TokenStatus removeAllTokens(HttpServletRequest request) {

		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();

		String token = getToken(request);

		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("request", "singout-from-alldevices");

		dto.setToken(map);

		TokenStatus tokenStatus = apiGatewayRequestUri.invalidateTokens(dto).getBody();

		return tokenStatus;
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

	public TokenStatus defaultFallbackMethodHandleRequest(HttpServletRequest request, Throwable exception) {
		TokenStatus tokenStatus = new TokenStatus();
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

}
