package frontend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response);

	void invalidateToken(HttpServletRequest request);

	TokenStatus changePassword(String password, String token);

	TokenStatus removeAllTokens(HttpServletRequest request);

	LoginStatus createAuthenticationToken(UserCredential userCredential);

	CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto,HttpServletRequest request, HttpServletResponse response);

}
