package frontend.service;

import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.AddToCartRequest;
import frontend.controller.LoginStatus;
import frontend.response.ResetPasswordResponse;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response);

	TokenStatus invalidateToken(HttpServletRequest request);

	ResponseConstant changePassword(ChangePasswordReqest request);

	TokenStatus removeAllTokens(TokenStatus tokenStatus);

	LoginStatus createAuthenticationToken(UserCredentialRequest userCredential, HttpServletRequest request,
										  HttpServletResponse response);

	CreateUserResponseStatus register(Message message,CreateUserRequestDto createUserRequestDto);

	frontend.response.AddToCartResponse addToCart(AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response);

	AddToCartCountProductsResponse addToCartProductCount(String token, HttpServletRequest request, HttpServletResponse response);

	ResetPasswordResponse userNameCheck(String email);

	ResponseConstant authenticateIdentityToken(String code);

	}
