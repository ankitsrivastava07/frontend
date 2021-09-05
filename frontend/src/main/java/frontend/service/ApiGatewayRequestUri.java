package frontend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import frontend.controller.ChangePasswordReqest;
import frontend.controller.ChangePasswordRequestDto;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;
import frontend.dto.AddToCartRequest;
import frontend.response.AddToCartResponse;
@FeignClient(name = "cloud-gateway-spring", url = "http://cloud-gateway-spring.herokuapp.com")
public interface ApiGatewayRequestUri {

	@PostMapping("/users/login")
	public ResponseEntity<LoginStatus> createAuthenticationToken(@RequestBody UserCredential userCredential);

	@PostMapping("/users/register")
	public ResponseEntity<CreateUserResponseStatus> register(@RequestBody CreateUserRequestDto createUserRequestDto);

	@PostMapping("/users/change-password")
	public ResponseEntity<TokenStatus> changePassword(@RequestBody ChangePasswordReqest request);

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

	@PostMapping("/user/add-to-cart-product")
	public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest);

}
