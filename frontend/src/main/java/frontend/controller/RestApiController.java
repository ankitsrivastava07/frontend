package frontend.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import frontend.api.dto.response.UnauthorizedRequestURL;
import frontend.api.dto.response.UserDto;
import frontend.api.error.ApiError;
import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.request.UserNameExistRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.OrderRequest;
import frontend.dto.OrderResponseDto;
import frontend.response.ResetPasswordResponse;
import frontend.service.AddToCartCountProductsResponse;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import java.io.*;
import java.util.*;
@org.springframework.web.bind.annotation.RestController
@RequestMapping({"/api/v1/user/", "/user","/api/v1/user/profile"})
public class RestApiController {
	@Autowired
	private FrontendService frontendService;
    @Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtAccessTokenUtil jwtAccessTokenUtil;
	@RequestMapping(path = "/login",method=RequestMethod.POST)
	public ResponseEntity<?> signIn(@RequestBody @Valid UserCredentialRequest userCredential, HttpServletRequest request,
								   HttpServletResponse response) throws JsonProcessingException {
      try {
        UserCredentialRequest userCredentialRequest= (UserCredentialRequest) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getEmailOrMobile(),userCredential.getPassword()));
		LoginStatus loginStatus= new LoginStatus();
		loginStatus.setHttpStatus(userCredentialRequest.getHttpStatus());
		if(loginStatus.getHttpStatus()==503)
			loginStatus.setStatus(Boolean.FALSE);
		  else
			  loginStatus.setStatus(Boolean.TRUE);
		loginStatus.setToken(userCredentialRequest.getToken());
		loginStatus.setBrowser(userCredentialRequest.getBrowser());
		loginStatus.setMessage(userCredentialRequest.getMessage());
		return new ResponseEntity<>(loginStatus, HttpStatus.valueOf(loginStatus.getHttpStatus()));
	  }catch (BadCredentialsException exception){
		  LoginStatus loginStatus = new LoginStatus();
		  loginStatus.setHttpStatus(HttpStatus.OK.value());
		  loginStatus.setMessage("Invalid email/mobile and password");
		 // return null;
		 return new ResponseEntity<>(loginStatus, HttpStatus.valueOf(loginStatus.getHttpStatus()));
	  }
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateUserRequestDto createUserRequestDto,HttpServletRequest request,HttpServletResponse response) {
		CreateUserResponseStatus status = frontendService.register(createUserRequestDto);
		return new ResponseEntity<>(status, HttpStatus.valueOf(status.getHttpStatus()));
	}

	@PostMapping(path = "/product/add-to-cart")
	public ResponseEntity<?> addToCart(HttpServletResponse response) {
		TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
		return new ResponseEntity<>(tokenStatus.getMessage(),HttpStatus.valueOf(tokenStatus.getHttpStatus()));
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestHeader(name = "uriToken") String uriToken, @RequestBody ChangePasswordReqest req, HttpServletRequest request){
		String browser=UUID.randomUUID().toString();
		req.setBrowserName(browser);
		ResponseConstant responseConstant = frontendService.changePassword(req);
		return new ResponseEntity<>(responseConstant, HttpStatus.valueOf(responseConstant.getHttpStatus()));
	}

	@PostMapping("/save-order")
	public ResponseEntity<?> saveOrder(@RequestBody @Valid OrderRequest orderRequest){
     TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
		if(!tokenStatus.isStatus()) {
			ApiError apiError = new ApiError(new Date(),HttpStatus.UNAUTHORIZED.value(),"Your session has been expired","/save-order");
			return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
		}
		OrderResponseDto responseConstant = frontendService.saveOrder(tokenStatus.getAccessToken(),orderRequest);
		return new ResponseEntity<>(responseConstant,HttpStatus.valueOf(responseConstant.getHttpStatus()));
	}

	@PostMapping("/profile/edit")
	public ResponseEntity<?> editProfile(UserDto userDto,@RequestParam(name="image",required = false) MultipartFile multipartFile) throws IOException {
		TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
		if (tokenStatus!=null && tokenStatus.isStatus() ){
			userDto.setBrowser(tokenStatus.getBrowser());
			if(multipartFile!=null && !frontendService.isValidFileExtension(multipartFile)){
				ApiError apiError = new ApiError(new Date(),HttpStatus.BAD_REQUEST.value(),ResponseConstant.FILE_EXTENSION_VALIDATION_FAILED_DEFAULT_MESSAGE,null);
				  return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
			}
		}
		userDto=frontendService.editProfile(tokenStatus.getAccessToken(),userDto,multipartFile);
		return new ResponseEntity<>(userDto, HttpStatus.valueOf(userDto.getHttpStatus()));
	}

/*
	@PostMapping(name = "/product/add-to-cart")
	public ResponseEntity<?> addToCartProductDetail(@RequestHeader("Authentication")String accessToken, @RequestBody AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response) {
		frontendService.addToCart(addToCartRequest, request, response);
		return null;
	}
*/

	//@PreAuthorize("has_ROLE(USER)")
	@PostMapping(name = "/product/add-to-cart")
	public ResponseEntity<?> addToCartCountProducts(@RequestHeader(name="Authentication",required = false) String authentication) {
		if(StringUtils.isEmpty(authentication))
			return new ResponseEntity<>("Unauthorize requseet",HttpStatus.UNAUTHORIZED);
		AddToCartCountProductsResponse addToCartCountProductsResponse = new AddToCartCountProductsResponse();
		TokenStatus tokenStatus = frontendService.isValidToken(authentication);
		if (tokenStatus.isStatus()) {
			addToCartCountProductsResponse = frontendService.addToCartProductCount(tokenStatus.getUserId());
			return new ResponseEntity<>(addToCartCountProductsResponse,HttpStatus.OK);
		}
		return new ResponseEntity<>(addToCartCountProductsResponse,HttpStatus.valueOf(tokenStatus.getHttpStatus()));
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validate(@RequestBody @Valid UserNameExistRequest userNameExistRequest) {
		ResetPasswordResponse resetPasswordResponse = frontendService.validateUser(userNameExistRequest.getEmail());
		return new ResponseEntity<>(resetPasswordResponse, HttpStatus.valueOf(resetPasswordResponse.getHttpStatus()));
	}

	@PostMapping("/validate-session")
	public ResponseEntity<?> validateSession(@RequestHeader(name = "Authentication", required = false) String authentication) {
		if(authentication==null)
			return new ResponseEntity<>("Unauthorize request",HttpStatus.UNAUTHORIZED);
		TokenStatus tokenStatus = frontendService.isValidToken(authentication);
		if(tokenStatus.isStatus())
			return new ResponseEntity<>(tokenStatus,HttpStatus.valueOf(tokenStatus.getHttpStatus()));
		return new ResponseEntity<>(tokenStatus,HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/auth/token/refresh")
	public ResponseEntity<?> refreshAccessToken(@RequestHeader(name="Authentication")String authentication,@RequestHeader("browser")String browser){
		TokenStatus tokenStatus=frontendService.refreshToken(authentication,browser);
	  return tokenStatus.isStatus()?new ResponseEntity<>(tokenStatus,HttpStatus.valueOf(tokenStatus.getHttpStatus())) : new ResponseEntity<>(tokenStatus,HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping("/unauthorzied-request")
	public ResponseEntity<?> UnauthorizedRequest(HttpServletRequest request){
		String token=request.getHeader("Authentication");
		TokenStatus tokenStatus= frontendService.isValidToken(token);
		UnauthorizedRequestURL unauthorizedRequest = new UnauthorizedRequestURL();
		unauthorizedRequest.setRedirect(Boolean.TRUE);
		unauthorizedRequest.setRedirectURL("/signin");
		unauthorizedRequest.setMessage(tokenStatus.getMessage());
		unauthorizedRequest.setTitle(ResponseConstant.UNAUTHORIZE_REQUEST_DEAULT_MESSAGE);
		return new ResponseEntity<>(unauthorizedRequest,HttpStatus.UNAUTHORIZED);
	}

}
