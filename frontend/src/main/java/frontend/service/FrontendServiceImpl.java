package frontend.service;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import frontend.api.dto.response.UserDto;
import frontend.api.error.ApiError;
import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.ChangePasswordRequestDto;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.OrderRequest;
import frontend.dto.OrderResponseDto;
import frontend.response.AddToCartResponse;
import frontend.response.ResetPasswordResponse;
import io.github.resilience4j.circuitbreaker.internal.CircuitBreakerStateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import frontend.controller.LoginStatus;
import frontend.dto.AddToCartRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FrontendServiceImpl implements FrontendService {
	Logger logger = Logger.getLogger("Response of microservice");
	@Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
	@Autowired HttpServletRequest httpServletRequest;
	@Autowired HttpServletResponse httpServletResponse;
	@Autowired private AmazonS3 amazonS3;
	@Value("${aws.s3.bucket}")
	@Autowired private String bucketName;
	private List<String> fileExtension= new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();
	public FrontendServiceImpl(){
		fileExtension.add("image/jpg");
		fileExtension.add("image/jpeg");
		fileExtension.add("image/png");
		fileExtension.add("image/gif");
	}
	@Override
	public void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName, String cookieValue) {
		Cookie cookies[] = request.getCookies();
		if (!cookieValue.isEmpty() && cookies != null) {
			for (Cookie cookie : cookies)
				if (cookie.getName().equalsIgnoreCase(cookieName)) {
					cookie.setValue(cookieValue);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					return;
				}
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		response.addCookie(cookie);
	}

	@Override
	public String getToken(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		String jwtToken = null;
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equalsIgnoreCase("session_Token"))
					return jwtToken = cookie.getValue();
		return jwtToken;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "isValidTokenFallback")
	public TokenStatus isValidToken(String jwtToken) {
		TokenStatus tokenStatus = apiGatewayRequestUri.isValidToken(jwtToken).getBody();
			if (tokenStatus!=null && tokenStatus.isStatus() && tokenStatus.getIsAccessTokenNewCreated())
				setCookie(httpServletRequest, httpServletResponse,"session_Token", tokenStatus.getAccessToken());
			return tokenStatus;
	}

	public TokenStatus isValidTokenFallback(String jwtToken, Throwable exception) {
		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		tokenStatus.setHttpStatus(503);
		logger.info(exception.getMessage());
		return tokenStatus;
	}

	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "invalidateFallBack")
	public TokenStatus invalidateToken(String authToken) {
		TokenStatus tokenStatus = null;
		if (Objects.nonNull(authToken) && !authToken.isEmpty())
		tokenStatus = apiGatewayRequestUri.invalidateSession(authToken).getBody();
		CircuitBreakerStateMachine circuitBreaker= new CircuitBreakerStateMachine("cloud-gateway-spring");
		io.github.resilience4j.circuitbreaker.CircuitBreaker.State state=circuitBreaker.getState();
		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "changePasswordFallback")
	public ResponseConstant changePassword(ChangePasswordReqest request) {
		ResponseConstant responseConstant = apiGatewayRequestUri.changePassword(request).getBody();
		return responseConstant;
	}

	public ResponseConstant changePasswordFallback(ChangePasswordReqest request, Throwable exception){
		ResponseConstant responseConstant = new ResponseConstant();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Sorry Server is currently down.Please try again later");
		responseConstant.setHttpStatus(503);
		logger.info(exception.getMessage());
		return responseConstant;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "removeTokensFallbackMethod")
	public TokenStatus removeAllTokens(TokenStatus tokenStatus) {
		ChangePasswordRequestDto dto = new ChangePasswordRequestDto();
		dto.setUserId(tokenStatus.getUserId());
		dto.setToken(tokenStatus.getAccessToken());
		Map<String, String> map = new HashMap<>();
		TokenStatus tokenStatus1 = apiGatewayRequestUri.invalidateTokens(dto).getBody();
		return tokenStatus1;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "loginFallBack")
	public LoginStatus createAuthenticationToken(UserCredentialRequest userCredential) {
		LoginStatus loginStatus = apiGatewayRequestUri.createAuthenticationToken(userCredential).getBody();
		return loginStatus;
	}

	public LoginStatus loginFallBack(UserCredentialRequest userCredential,Throwable exception) {
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setMessage("Sorry Server is currently down.Please try again later");
		loginStatus.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		logger.info(exception.getMessage());
		return loginStatus;
	}

	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "registerFallBack")
	public CreateUserResponseStatus register(CreateUserRequestDto createUserRequestDto) {
		createUserRequestDto.setBrowser(UUID.randomUUID().toString());
		CreateUserResponseStatus responseStatus=apiGatewayRequestUri.register(createUserRequestDto).getBody();
	 return responseStatus;
 }
	public CreateUserResponseStatus registerFallBack(CreateUserRequestDto createUserRequestDto,Throwable exception) {
		CreateUserResponseStatus createUserResponseStatus = new CreateUserResponseStatus();
		createUserResponseStatus.setMessage("Sorry Server is currently down.Please try again later");
		createUserResponseStatus.setHttpStatus(503);
		logger.info(exception.getMessage());
		return createUserResponseStatus;
	}

	public TokenStatus tokenValidFallback(HttpServletRequest request, HttpServletResponse response,
			Throwable exception) {

		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		tokenStatus.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		logger.info(exception.getMessage());
		return tokenStatus;
	}

	public TokenStatus defaultFallbackMethodHandleRequest(ChangePasswordReqest request, Throwable exception) {
		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		logger.info(exception.getMessage());
		return tokenStatus;
	}

	public TokenStatus removeTokensFallbackMethod(TokenStatus tokenStatus, Throwable exception) {
		TokenStatus tokenStatus2 = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		logger.info(exception.getMessage());
		return tokenStatus;
	}

	public TokenStatus invalidateFallBack(String jwtTOken, Throwable exception) {
		TokenStatus tokenStatus = new TokenStatus();
		System.out.println(exception.getMessage());
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		logger.info(exception.getMessage());
		return tokenStatus;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "addToCartFallBack")
	public AddToCartResponse addToCart(AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response) {
		AddToCartResponse addToCartResponse = apiGatewayRequestUri.addToCart(addToCartRequest).getBody();
		if(addToCartResponse.getIsAccessTokenNewCreated()) {
			setCookie(request,response,"session_Token",addToCartResponse.getSessionToken());
		}
		return addToCartResponse;
	}

	public AddToCartResponse addToCartFallBack(AddToCartRequest addToCartRequest,HttpServletRequest request, HttpServletResponse response,Throwable exception) {
		AddToCartResponse addToCartResponse = new AddToCartResponse();
		addToCartResponse.setStatus(Boolean.FALSE);
		addToCartResponse.setSessionToken(null);
		addToCartResponse.setIsAccessTokenNewCreated(Boolean.FALSE);
		addToCartResponse.setCreatedAt(LocalDateTime.now());
		addToCartResponse.setMessage("Sorry Server is currently down.Please try again later");
		logger.info(exception.getMessage());
		return addToCartResponse;
	}
	
	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "addToCartProductCountFallbackMethod")
	public AddToCartCountProductsResponse addToCartProductCount(Long userId) {

		AddToCartCountProductsResponse addToCartResponse = apiGatewayRequestUri.addToCartProductCount(userId).getBody();
		if(addToCartResponse.getIsAccessTokenNewCreated()) {
		}
		return addToCartResponse;
	}

  public AddToCartCountProductsResponse addToCartProductCountFallbackMethod(String token,HttpServletRequest request, HttpServletResponse response,Throwable exception) {
	  AddToCartCountProductsResponse addToCartResponse = new AddToCartCountProductsResponse();
		addToCartResponse.setStatus(Boolean.FALSE);
		addToCartResponse.setSessionToken(null);
		addToCartResponse.setIsAccessTokenNewCreated(Boolean.FALSE);
		//addToCartResponse.setCreatedAt(LocalDateTime.now());
		addToCartResponse.setMessage("Sorry Server is currently down.Please try again later");
	  logger.info(exception.getMessage());
		return addToCartResponse;
	}
	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "userNameCheckFallback")
	public ResetPasswordResponse validateUser(String email){
		ResetPasswordResponse resetPasswordResponse = apiGatewayRequestUri.validateUser(email).getBody();
		return resetPasswordResponse;
	}
	public ResetPasswordResponse userNameCheckFallback(String email,Throwable exception){
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		resetPasswordResponse.setStatus(Boolean.FALSE);
		resetPasswordResponse.setMessage("Sorry Server is currently down.Please try again later");
		resetPasswordResponse.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		logger.info("user microservice response "+exception.getMessage());
		return resetPasswordResponse;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring", fallbackMethod = "authenticateIdentityTokenFallback")
	public ResponseConstant authenticateIdentityToken(String code) {
		ResponseConstant responseConstant = apiGatewayRequestUri.authenticateIdentityToken(code).getBody();
		return responseConstant;
	}

	public ResponseConstant authenticateIdentityTokenFallback(String email,Throwable exception){
		ResponseConstant responseConstant = new ResponseConstant();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Sorry Server is currently down.Please try again later");
		responseConstant.setHttpStatus(503);
		return responseConstant;
	}

	@Override
	@CircuitBreaker(name = "cloud-gateway-spring",fallbackMethod="saveOrderFallback")
	public OrderResponseDto saveOrder(String accessToken,OrderRequest request) {
		OrderResponseDto orderResponseDto=(OrderResponseDto)apiGatewayRequestUri.saveOrder(accessToken,request).getBody();
		return orderResponseDto;
	}

	@Override
	@CircuitBreaker(name="cloud-gateway-spring",fallbackMethod = "refreshTokenFallBack")
	public TokenStatus refreshToken(String authentication, String browser) {
		TokenStatus tokenStatus= apiGatewayRequestUri.refreshToken(authentication,browser).getBody();
		return tokenStatus;
	}

	public TokenStatus refreshTokenFallBack(String authentication, String browser,Throwable exception) {
		logger.info(exception.getMessage());
		System.out.println(exception.getMessage());
		TokenStatus tokenStatus = new TokenStatus();
		tokenStatus.setMessage("Sorry Server is currently down.Please try again later");
		return tokenStatus;
	}

	public OrderResponseDto saveOrderFallback(String accessToken,OrderRequest request,Throwable exception) {

		OrderResponseDto responseConstant = new OrderResponseDto();
		responseConstant.setMessage("Sorry Server is currently down.Please try again later");
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setOrderStatus("Unable to save your order");
		responseConstant.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		responseConstant.setPath(httpServletRequest.getRequestURI());
		return responseConstant;
	}

	@Override
	@CircuitBreaker(name="cloud-gateway-spring",fallbackMethod = "profileFallBack")
	public UserDto profile(String authentication,String browser) {
		UserDto userDto=null;
			try {
				userDto =  apiGatewayRequestUri.profile(authentication).getBody();
				if (userDto.getS3BucketFileName()!=null) {
					String url = userDto.getS3BucketFileURL();
					S3Object s3Object = amazonS3.getObject(bucketName+"/profile_image", userDto.getS3BucketFileName());
					String enc = "data:" + userDto.getContentType() + ";base64," + Base64.getEncoder().encodeToString(IOUtils.toByteArray(s3Object.getObjectContent()));
					userDto.setS3BucketFileURL(enc);
					return userDto;
				}
				return userDto;
			} catch (IOException ioException){
				ioException.printStackTrace();
				return userDto;
			}
			catch (AmazonS3Exception exception){
				exception.printStackTrace();
				return userDto;
			}
	}

	public UserDto profileFallBack(String authentication,String browser,Throwable exception) {
		UserDto responseConstant = new UserDto();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Server down please try again later.");
		responseConstant.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		return responseConstant;
	}

	@Override
	@CircuitBreaker(name="cloud-gateway-spring",fallbackMethod = "editProfileFallBack")
	public Object editProfile(String authentication ,String browserCode,UserDto userDto,MultipartFile multipartFile) {
		try {
			String alternateMobile = userDto.getAlternateMobile();
			if (alternateMobile != null && userDto.getAlternateMobile().equals(""))
				userDto.setAlternateMobile(null);
			if (userDto.getEmail() != null && userDto.getEmail().equals(""))
				userDto.setEmail(null);
			if (multipartFile != null) {
				userDto.setFileName(multipartFile.getOriginalFilename().replaceAll("\\s",""));
				userDto.setContentType(multipartFile.getContentType());
				userDto.setContents(multipartFile.getBytes());
				userDto.setFileSize(Short.valueOf((short) ((short) multipartFile.getSize() * 0.00000095367432)));
				userDto.setPath(multipartFile.getName());
			}
			UserDto userDto1 = apiGatewayRequestUri.editProfile(authentication,browserCode, userDto).getBody();
			if(Objects.nonNull(userDto1.getIsSessionExpired()) && userDto1.getIsSessionExpired()){
				ApiError apiError = new ApiError(new Date(), HttpStatus.UNAUTHORIZED.value(),ResponseConstant.SESSION_EXPIRED_DEFAULT_MESSAGE,null);
				return apiError;
			}
			return userDto1;
		}catch (IOException exception){
			exception.printStackTrace();
			return null;
		}
	}

	public UserDto editProfileFallBack(String authentication,String browserCode,UserDto userDto,MultipartFile multipartFile,Throwable exception) {
		UserDto responseConstant = new UserDto();
		responseConstant.setStatus(Boolean.FALSE);
		responseConstant.setMessage("Server down please try again later.");
		responseConstant.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		return responseConstant;
	}

	@Override
	public boolean isValidFileExtension(MultipartFile multipartFile){
		return fileExtension.contains(multipartFile.getContentType());
	}

}

