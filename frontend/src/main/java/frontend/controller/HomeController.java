package frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import frontend.dto.AddToCartRequest;
import frontend.response.AddToCartResponse;
import frontend.service.AddToCartCountProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;

import java.io.IOException;

@RestController
@RequestMapping("")
public class HomeController {

	@Autowired
	private FrontendService frontendService;

	@GetMapping({ "/", "", "/home" })
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("userName", "");
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && tokenStatus.isStatus())
			model.addObject("userName", tokenStatus.getFirstName());
		return model;
	}

	@GetMapping({ "/contact" })
	public ModelAndView contactUs(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("contact-us");
		return model;
	}

	@RequestMapping(value = "/", method = { RequestMethod.POST })
	public void contactUs(@RequestParam("redirect")String redirectUrl,HttpServletResponse response) throws IOException {
		response.sendRedirect(redirectUrl);
		return ;
	}

	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	public ResponseEntity<?> login(@RequestBody UserCredential userCredential, HttpServletRequest request,
								   HttpServletResponse response) throws JsonProcessingException {
		LoginStatus loginStatus = frontendService.createAuthenticationToken(userCredential, request, response);
		return new ResponseEntity<>(loginStatus, HttpStatus.OK);
	}

	@GetMapping("/signout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		frontendService.invalidateToken(request);
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
	public ResponseEntity<?> register(@RequestBody CreateUserRequestDto createUserRequestDto,
									  HttpServletRequest request, HttpServletResponse response) {

		String email = createUserRequestDto.getEmail().length() == 0 ? null : createUserRequestDto.getEmail();
		createUserRequestDto.setEmail(email);
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && tokenStatus.isStatus()) {
			tokenStatus.setAccessToken(null);
			tokenStatus.setMessage(null);
			tokenStatus.setFirstName(null);
			tokenStatus.setLogined(true);
			return new ResponseEntity<>(tokenStatus, HttpStatus.OK);
		}
		CreateUserResponseStatus status = frontendService.register(createUserRequestDto, request, response);
		status.setToken(null);
		if (!status.isStatus() && status.getHttpStatus() != null && status.getHttpStatus() == 503)
			return new ResponseEntity<>(status.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		else if (!status.isStatus())
			return new ResponseEntity<>(status, HttpStatus.OK);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/signin")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && !tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView();
			model.addObject("message", tokenStatus.getMessage());
			model.setViewName("login");
			return model;
		}
		else if (tokenStatus != null && tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView("redirect:" + "/");
			return model;
		}
		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/change-password")
	public ModelAndView changePasswod(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("change-password");
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus == null || tokenStatus != null && !tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView("redirect:" + "/signin");
			model.setStatus(HttpStatus.OK);
			return model;
		}
		return mv;
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestHeader(value = "session_Token") String token,
											@RequestBody ChangePasswordReqest req, HttpServletRequest request, HttpServletResponse response) {
		req.setToken(token);
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && tokenStatus.isStatus()) {
			req.setUserId(tokenStatus.getUserId());
			tokenStatus = frontendService.changePassword(req);
		}
		return new ResponseEntity<>(tokenStatus, HttpStatus.OK);
	}

	@GetMapping("/signout-from-all-devices")
	public void signOutFromAllDevices(@RequestParam("redirect") String urlRedirect,HttpServletRequest request, HttpServletResponse response) throws IOException {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus == null || tokenStatus != null && !tokenStatus.isStatus())
		   response.sendRedirect("/signin");
		frontendService.removeAllTokens(request);
		 response.sendRedirect(urlRedirect);;
	}

	@GetMapping("/orders")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus == null || tokenStatus != null && !tokenStatus.isStatus()) {
			ModelAndView model = new ModelAndView("redirect:" + "/signin");
			model.setStatus(HttpStatus.OK);
			return model;
		}
		frontendService.removeAllTokens(request);
		return new ModelAndView("redirect:" + "/");
	}

	@RequestMapping("*/error")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		ModelAndView mv = new ModelAndView("forward:" + "/");
		mv.setViewName("error/error-500");
		return mv;
	}

	@RequestMapping("/popup")
	public ModelAndView responsePopUp(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null && !tokenStatus.isStatus()) {
			return new ModelAndView("redirect:" + "/signin");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/response-html/popup");
		return mv;
	}

	@GetMapping("/account")
	public ModelAndView profile(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("profile");
		return mv;
	}

	@GetMapping("/check-connection")
	public ResponseEntity<?> checkConnection(){
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/product/add-to-cart")
	public ModelAndView addToCart(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("add-to-cart-detail-page");
		return mv;
	}

	@PostMapping(value = "/product/add-to-cart")
	public ResponseEntity<?> addToCartProductDetail(@RequestBody AddToCartRequest addToCartRequest,HttpServletRequest request,HttpServletResponse response){
		frontendService.addToCart(addToCartRequest,request,response);
		return null;
	}

	@RequestMapping(value="/product/add-to-cart-count-products" ,method=RequestMethod.GET)
	public AddToCartCountProductsResponse addToCartCountProducts(@RequestParam(required = false,name="sessionToken") String sessionToken,HttpServletRequest request,HttpServletResponse response){
		System.out.println("add-to-cart-count-products method called");
		AddToCartCountProductsResponse addToCartCountProductsResponse = new AddToCartCountProductsResponse();
		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if(tokenStatus.isStatus()) {
			AddToCartCountProductsResponse addToCartCountProducts= frontendService.addToCartProductCount(sessionToken,request,response);
			return addToCartCountProducts;
		}
		return addToCartCountProductsResponse;
	}

}