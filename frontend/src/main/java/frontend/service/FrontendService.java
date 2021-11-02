package frontend.service;

import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frontend.api.dto.response.UserDto;
import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.AddToCartRequest;
import frontend.controller.LoginStatus;
import frontend.dto.OrderRequest;
import frontend.dto.OrderResponseDto;
import frontend.response.ResetPasswordResponse;

public interface FrontendService {

	void setCookie(HttpServletRequest request, HttpServletResponse response, String token);

	TokenStatus isValidToken(HttpServletRequest request, HttpServletResponse response);

	TokenStatus isValidToken(String authorizationToken);

	TokenStatus invalidateToken(String authToken);

	ResponseConstant changePassword(ChangePasswordReqest request);

	TokenStatus removeAllTokens(TokenStatus tokenStatus);

	LoginStatus createAuthenticationToken(UserCredentialRequest userCredential);

	CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto);

	frontend.response.AddToCartResponse addToCart(AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response);

	AddToCartCountProductsResponse addToCartProductCount(Long userId);

	ResetPasswordResponse userNameCheck(String email);

	ResponseConstant authenticateIdentityToken(String code);

	OrderResponseDto saveOrder(String accessToken,OrderRequest request);

	TokenStatus refreshToken(String authentication,String browser);

	UserDto editProfile(String authentication,UserDto userDto);
	UserDto profile(String authentication,String browser);
	}

