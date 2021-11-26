package frontend.service;

import frontend.api.dto.response.UserDto;
import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.ChangePasswordRequestDto;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.OrderRequest;
import frontend.response.ResetPasswordResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import frontend.controller.LoginStatus;
import frontend.dto.AddToCartRequest;
import frontend.response.AddToCartResponse;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cloud-gateway-spring", url = "http://localhost:8765")
public interface ApiGatewayRequestUri {

	@PostMapping("/users/login")
	public ResponseEntity<LoginStatus> createAuthenticationToken(@RequestBody UserCredentialRequest userCredential);

	@PostMapping("/users/register")
	public ResponseEntity<CreateUserResponseStatus> register(@RequestBody CreateUserRequestDto createUserRequestDto);

	@PostMapping("/users/change-password")
	public ResponseEntity<ResponseConstant> changePassword(@RequestBody ChangePasswordReqest request);

	@PostMapping("/users/get-first-name")
	public ResponseEntity<String> getFirstName(@RequestBody String token);

	@PostMapping("/users/findEmail")
	public ResponseEntity<UserDto> findByEmail(@RequestHeader(name="email",required = true) String email);

	@PostMapping("/token-session/validate-token")
	public ResponseEntity<TokenStatus> isValidToken(@RequestHeader("Authentication") String authentication);

	@PostMapping("/token-session/invalidate-session")
	public ResponseEntity<TokenStatus> invalidateSession(@RequestHeader("Authentication") String authentication);

	@PostMapping("/token-session/invalidate-tokens")
	public ResponseEntity<TokenStatus> invalidateTokens(@RequestBody ChangePasswordRequestDto dto);
	
	@PostMapping("/users/add-to-cart-product-count")
	public ResponseEntity<AddToCartCountProductsResponse> addToCartProductCount(@RequestBody Long userId);

	@PostMapping("/users/add-to-cart-product")
	public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest);

	@PostMapping("/users/userName/check")
	public ResponseEntity<ResetPasswordResponse> userNameCheck(@RequestBody String email);

	@PostMapping("/users/auth/identity-token")
	ResponseEntity<ResponseConstant> authenticateIdentityToken(@RequestBody String code);
	@PostMapping("/orders/save-order")
	ResponseEntity<?> saveOrder(@RequestHeader(name="Authentication")String authentication, @RequestBody OrderRequest orderRequest);

	@PostMapping("/token_session")
	ResponseEntity<TokenStatus> refreshToken(@RequestHeader("Authentication")String authentication,@RequestHeader("browser")String browser);

	@PostMapping("/users/profile")
	ResponseEntity<UserDto> profile(@RequestHeader("Authentication")String authentication,@RequestHeader(value = "Browser",required = false)String Browser);

	@PostMapping("/users/profile/edit")
	ResponseEntity<UserDto> editProfile(@RequestHeader("Authentication")String authentication,@RequestBody UserDto userDto);

}
