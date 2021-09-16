package frontend.service;

import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.ChangePasswordRequestDto;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.response.ResetPasswordResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import frontend.controller.LoginStatus;
import frontend.dto.AddToCartRequest;
import frontend.response.AddToCartResponse;
@FeignClient(name = "users", url = "http://cloud-gateway-spring.herokuapp.com/")
public interface ApiGatewayRequestUri {

	@PostMapping("/users/login")
	public ResponseEntity<LoginStatus> createAuthenticationToken(@RequestBody UserCredentialRequest userCredential);

	@PostMapping("/users/register")
	public ResponseEntity<CreateUserResponseStatus> register(@RequestBody CreateUserRequestDto createUserRequestDto);

	@PostMapping("/users/change-password")
	public ResponseEntity<ResponseConstant> changePassword(@RequestBody ChangePasswordReqest request);

	@PostMapping("/users/get-first-name")
	public ResponseEntity<String> getFirstName(@RequestBody String token);

	@PostMapping("/token-session/validate-token")
	public ResponseEntity<TokenStatus> isValidToken(@RequestBody(required = false) String jwt);

	@PostMapping("/token-session/invalidate-token")
	public ResponseEntity<TokenStatus> invalidateToken(@RequestBody String token);

	@PostMapping("/token-session/invalidate-tokens")
	public ResponseEntity<TokenStatus> invalidateTokens(@RequestBody ChangePasswordRequestDto dto);
	
	@PostMapping("/users/add-to-cart-product-count")
	public ResponseEntity<AddToCartCountProductsResponse> addToCartProductCount(@RequestBody String token);

	@PostMapping("/users/add-to-cart-product")
	public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest);

	@PostMapping("/users/userName/check")
	public ResponseEntity<ResetPasswordResponse> userNameCheck(@RequestBody String email);

	@PostMapping("/users/auth/identity-token")
	public ResponseEntity<ResponseConstant> authenticateIdentityToken(@RequestBody String code);
}
