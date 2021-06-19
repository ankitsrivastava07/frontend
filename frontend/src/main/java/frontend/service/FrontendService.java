package frontend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.controller.ChangePasswordReqest;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response);

	TokenStatus invalidateToken(HttpServletRequest request);

	TokenStatus changePassword(ChangePasswordReqest request);

	TokenStatus removeAllTokens(HttpServletRequest request);

	LoginStatus createAuthenticationToken(UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response);

	CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto, HttpServletRequest request,
			HttpServletResponse response);

}
