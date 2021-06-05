package frontend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.controller.LoginStatus;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request,HttpServletResponse response);

	void invalidateToken(HttpServletRequest request);

	ChangePasswordResponseStatus changePassword(String password, String token);

	TokenStatus removeAllTokens(HttpServletRequest request);
}
