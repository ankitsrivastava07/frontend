package frontend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import frontend.controller.ChangePasswordRequestDto;
import frontend.controller.CreateUserRequestDto;
import frontend.controller.CreateUserResponseStatus;
import frontend.controller.LoginStatus;
import frontend.controller.UserCredential;

@FeignClient(name = "api-gateway", url = "localhost:8765")
public interface ApiGatewayRequestUri {

	@PostMapping("/users/login")
	public ResponseEntity<LoginStatus> createAuthenticationToken(@RequestBody UserCredential userCredential);

	@PostMapping("/users/register")
	public ResponseEntity<CreateUserResponseStatus> register(@RequestBody CreateUserRequestDto createUserRequestDto);

	@PostMapping("/users/change-password")
	public ResponseEntity<ChangePasswordResponseStatus> changePassword(
			@RequestBody ChangePasswordRequestDto changePasswordRequest);

	@PostMapping("/users/get-first-name")
	public ResponseEntity<String> getFirstName(@RequestBody String token);

	@PostMapping("/token-session/validate-token")
	public ResponseEntity<TokenStatus> isValidToken(@RequestBody String jwt);

	@PostMapping("/token-session/invalidate-token")
	public ResponseEntity<TokenStatus> invalidateToken(@RequestBody String token);

	@PostMapping("/token-session/invalidate-tokens")
	public ResponseEntity<TokenStatus> invalidateTokens(@RequestBody ChangePasswordRequestDto dto);

}
