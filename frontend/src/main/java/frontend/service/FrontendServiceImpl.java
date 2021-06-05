package frontend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import frontend.controller.ChangePasswordRequestDto;

@DefaultProperties(defaultFallback = "defaultFallbackMethodHandleRequest")
@Service
public class FrontendServiceImpl implements FrontendService {

	@Autowired
	private ApiGatewayRequestUri apiGatewayRequestUri;

	@Override
	public void setCookie(HttpServletRequest request, HttpServletResponse response, String token) {

		Cookie cookies[] = request.getCookies();

		if (cookies != null)

			for (Cookie cookie : cookies)

				if (cookie.getName().equalsIgnoreCase("session_Token")) {
					cookie.setValue(token);
					cookie.setPath("/");
					return;
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

	//@HystrixCommand(fallbackMethod = "defaultFallbackMethodHandleRequest")
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

	public void invalidateToken(HttpServletRequest request) {

		String token = getToken(request);

		TokenStatus tokenStatus = null;

		if (Objects.nonNull(token) && !token.isEmpty())

			tokenStatus = apiGatewayRequestUri.invalidateToken(token).getBody();

	}

	@Override
	public ChangePasswordResponseStatus changePassword(String password, String token) {

		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();

		Map<String, String> map = new HashMap<>();

		map.put("password", password);

		map.put("token", token);

		map.put("request", "change-password");

		dto.setToken(map);

		ChangePasswordResponseStatus changePasswordResponseStatus = apiGatewayRequestUri.changePassword(dto).getBody();

		return changePasswordResponseStatus;
	}

	@Override
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

	public String defaultFallbackMethodHandleRequest() {

		return "redirect:/error";
	}

}
