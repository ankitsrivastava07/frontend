package frontend.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import frontend.api.dto.response.UserDto;
import frontend.api.request.ChangePasswordReqest;
import frontend.api.request.CreateUserRequestDto;
import frontend.api.request.UserCredentialRequest;
import frontend.api.request.UserNameExistRequest;
import frontend.api.response.CreateUserResponseStatus;
import frontend.constant.ResponseConstant;
import frontend.dto.AddToCartRequest;
import frontend.dto.OrderRequest;
import frontend.dto.OrderResponseDto;
import frontend.exceptionHandle.InvalidHeaderException;
import frontend.response.ResetPasswordResponse;
import frontend.service.AddToCartCountProductsResponse;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.tenant.TenantContext;
import lombok.val;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("")
public class HomeController {

	@Autowired
	private FrontendService frontendService;
    @Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtAccessTokenUtil jwtAccessTokenUtil;

	public HomeController() {
	}

	@GetMapping({"/", "", "/home"})
	public ModelAndView home(@RequestHeader(name = "Authentication",required = false)String authenticationToken,HttpServletRequest request,HttpServletResponse response) {
		TokenStatus tokenStatus=frontendService.isValidToken(request,response);
		ModelAndView model = new ModelAndView();
		model.addObject("userName","");
		if(tokenStatus!=null && tokenStatus.isStatus())
			model.addObject("userName",tokenStatus.getFirstName());
		model.setViewName("index");
	//	profile(request,response);
		return model;
	}
	@GetMapping({"/contact"})
	public ModelAndView contactUs(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("contact-us");
		return model;
	}

	@RequestMapping(value = "/", method = {RequestMethod.POST})
	public void contactUs(@RequestParam("redirect") String redirectUrl, HttpServletResponse response) throws IOException {
		response.sendRedirect(redirectUrl);
		return;
	}

	@GetMapping("/signin")
	public ModelAndView signin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		TokenStatus tokenStatus=frontendService.isValidToken(request,response);
		if (tokenStatus != null && tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/");
			return model;
		}
		if (tokenStatus != null && !tokenStatus.isStatus())
			mv.addObject("message", tokenStatus.getMessage());
		return mv;
	}

	@PostMapping("/signin")
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
		  response.addHeader("session_Token",loginStatus.getToken());
		  return new ResponseEntity<>(loginStatus, HttpStatus.valueOf(loginStatus.getHttpStatus()));
	  }catch (BadCredentialsException exception){
		  LoginStatus loginStatus = new LoginStatus();
		  loginStatus.setHttpStatus(HttpStatus.OK.value());
		  loginStatus.setMessage("Invalid email/mobile and password");
		  return new ResponseEntity<>(loginStatus, HttpStatus.valueOf(loginStatus.getHttpStatus()));
	  }
	}

	@GetMapping("/signout")
	public void signout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		TokenStatus tokenStatus=frontendService.isValidToken(request,response);
		tokenStatus=frontendService.invalidateToken(tokenStatus.getAccessToken());
		response.sendRedirect("/signin");
	}

	@GetMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sign-up");
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && tokenStatus.isStatus())
			return new ModelAndView("redirect:" + "/");
		return mv;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateUserRequestDto createUserRequestDto,HttpServletRequest request,HttpServletResponse response) {
		String browser=request.getHeader("User-agent");
		createUserRequestDto.setBrowser(browser);
		CreateUserResponseStatus status = frontendService.register(createUserRequestDto);
		response.addHeader("session_Tokehn",status.getToken());
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/change-password")
	public ModelAndView changePasswod(@RequestParam(value = "code", required = true) String code, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		ResponseConstant responseConstant = frontendService.authenticateIdentityToken(code);
		if (!responseConstant.getStatus()) {
			mv.setViewName("token_valid");
			mv.addObject("message", responseConstant.getMessage());
			return mv;
		}
		mv.setViewName("change-password");
		return mv;
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestHeader(value = "Authorization") String token, @RequestBody ChangePasswordReqest req, HttpServletRequest request){
		req.setBrowserName(request.getHeader("User-agent"));
		ResponseConstant responseConstant = frontendService.changePassword(req);
		return new ResponseEntity<>(responseConstant, HttpStatus.valueOf(responseConstant.getHttpStatus()));
	}

	@GetMapping("/signout-from-all-devices")
	public void signOutFromAllDevices(@RequestParam(value = "redirect") String urlRedirect, HttpServletRequest request, HttpServletResponse response) throws IOException {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		frontendService.removeAllTokens(tokenStatus);
		response.sendRedirect("/signin");
	}

	@GetMapping("/orders")
	public ModelAndView order() {
		TokenStatus tokenStatus=TenantContext.getCurrentTokenStatus();
		if (tokenStatus == null || tokenStatus != null && !tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView("redirect:" + "/signin");
			model.setStatus(HttpStatus.OK);
			return model;
		}
		return new ModelAndView("redirect:" + "/");
	}

	@PostMapping("/save-order")
	public ResponseEntity<?> saveOrder(@RequestHeader(name = "Authentication") String authentication,@RequestBody @Valid OrderRequest orderRequest){

		if(StringUtils.isEmpty(authentication))
			return new ResponseEntity<>("Un authroize request ",HttpStatus.UNAUTHORIZED);

		jwtAccessTokenUtil.validateToken(authentication);
		OrderResponseDto responseConstant = frontendService.saveOrder(authentication,orderRequest);
		return new ResponseEntity<>(responseConstant,HttpStatus.valueOf(responseConstant.getHttpStatus()));
	}

	@RequestMapping("*/error")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		ModelAndView mv = new ModelAndView("forward:" + "/");
		mv.setViewName("error/error-500");
		return mv;
	}

	@RequestMapping("/popup")
	public ModelAndView responsePopUp(HttpServletRequest request, HttpServletResponse response, Exception ex) {

		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && !tokenStatus.isStatus())
			return new ModelAndView("redirect:" + "/signin");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/response-html/popup");
		return mv;
	}

	@GetMapping("/users/profile")
	public ModelAndView profile(HttpServletRequest request,HttpServletResponse response) {
		TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile");
		UserDto userDto= null;
		if (tokenStatus!=null && tokenStatus.isStatus()){
			userDto=frontendService.profile(tokenStatus.getAccessToken(),tokenStatus.getBrowser());
			mv.addObject("userDto",userDto);
			if(userDto.getAddress()==null)
				mv.addObject("address","");
			else
			mv.addObject("address",userDto.getAddress().trim());
			return mv;
		}
		return new ModelAndView("redirect:" + "/signin");
	}

	@PostMapping("/users/profile/edit")
	public ResponseEntity<?> editProfile(HttpServletRequest request,@RequestHeader("Authentication")String authentication,@RequestBody UserDto userDto) {
		TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile");
		if (tokenStatus!=null && tokenStatus.isStatus()){
			userDto.setBrowser(userDto.getBrowser());
			userDto=frontendService.editProfile(tokenStatus.getAccessToken(),userDto);
			mv.addObject("firstName",userDto.getFirstName());
			mv.addObject("lastName",userDto.getLastName());
			mv.addObject("email",userDto.getEmail());
			mv.addObject("mobile",userDto.getMobile());
			mv.addObject("alterNameMobile",userDto.getAlternateMobile());
			return new ResponseEntity<>(userDto, HttpStatus.valueOf(userDto.getHttpStatus()));
		}
		return new ResponseEntity<>("Un authorize request", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/check-connection")
	public ResponseEntity<?> checkConnection() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/product/add-to-cart")
	public ModelAndView addToCart(HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		/*Cookie cookie= new Cookie("session_Token",tokenStatus.getAccessToken());
		response.addCookie(cookie);*/
		mv.setViewName("add-to-cart-detail-page");
		return mv;
	}

	@PostMapping(value = "/product/add-to-cart")
	public ResponseEntity<?> addToCartProductDetail(@RequestHeader("Authentication")String accessToken, @RequestBody AddToCartRequest addToCartRequest, HttpServletRequest request, HttpServletResponse response) {
		frontendService.addToCart(addToCartRequest, request, response);
		return null;
	}

	@RequestMapping(value = "/product/add-to-cart-count-products", method = RequestMethod.POST)
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

	@GetMapping("/users/auth")
	public ModelAndView userIdentityToken(@RequestParam(value = "code", required = false) String code) {
		ResponseConstant responseConstant = frontendService.authenticateIdentityToken(code);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("token_valid");
		if (!responseConstant.getStatus() && responseConstant.getHttpStatus() == 503) {
			mv.setViewName("token_valid");
			mv.addObject("message", responseConstant.getMessage());
			return mv;
		} else if (!responseConstant.getStatus()) {
			mv.setViewName("token_valid");
			mv.addObject("message", responseConstant.getMessage());
			return mv;
		}
		return new ModelAndView("redirect:" + "/change-password?code=" + code);
	}

	@GetMapping("/user/forget-password")
	public ModelAndView forgetPassword(@RequestParam(value = "code", required = false) String code, HttpServletRequest request, HttpServletResponse response) {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("reset-password");
		return mv;
	}

	@PostMapping("/userName/check")
	public ResponseEntity<?> userNameCheck(@RequestBody @Valid UserNameExistRequest userNameExistRequest) {
		ResetPasswordResponse resetPasswordResponse = frontendService.userNameCheck(userNameExistRequest.getEmail());
		return new ResponseEntity<>(resetPasswordResponse, HttpStatus.valueOf(resetPasswordResponse.getHttpStatus()));
	}

	@GetMapping("/redirect")
	public ModelAndView redirectToUrl(@RequestParam(value = "code", required = false) String code, HttpServletRequest request, HttpServletResponse response) {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forget-password");
		return mv;
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

}