package frontend.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.constant.ResponseConstant;
import frontend.dto.AddToCartRequest;
import frontend.controller.ChangePasswordReqest;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;
import frontend.response.AddToCartResponse;
import frontend.response.ResetPasswordResponse;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response);

	TokenStatus invalidateToken(HttpServletRequest request);

	ResponseConstant changePassword(ChangePasswordReqest request);

	TokenStatus removeAllTokens(TokenStatus tokenStatus);

	LoginStatus createAuthenticationToken(UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response);

	CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto, HttpServletRequest request,
			HttpServletResponse response);

	frontend.response.AddToCartResponse addToCart(AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response);

	AddToCartCountProductsResponse addToCartProductCount(String token, HttpServletRequest request, HttpServletResponse response);

	ResetPasswordResponse userNameCheck(String email);

	ResponseConstant authenticateIdentityToken(String code);

	}
