package frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import frontend.service.ApiGatewayRequestUri;
import frontend.service.ChangePasswordResponseStatus;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private FrontendService frontendService;

	@Autowired
	private ApiGatewayRequestUri apiGatewayRequestUri;

	@GetMapping({ "/", "/home" })
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("userName", "");

		TokenStatus tokenStatus = frontendService.isValidToken(request, response);
		if (tokenStatus != null)
			model.addObject("userName", tokenStatus.getFirstName());

		return model;
	}

	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	public ResponseEntity<?> login(@RequestBody UserCredential userCredential, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {

		ResponseEntity<LoginStatus> status = apiGatewayRequestUri.createAuthenticationToken(userCredential);

		LoginStatus loginStatus = status.getBody();

		if (loginStatus.isStatus())
			frontendService.setCookie(request, response, loginStatus.getToken());

		return new ResponseEntity<>(loginStatus.getMessage(), HttpStatus.OK);

	}

	@GetMapping("/signout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		frontendService.invalidateToken(request);
		return "redirect:/signin";
	}

	@GetMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sign-up");

		TokenStatus tokenStatus = frontendService.isValidToken(request, response);

		if (tokenStatus != null && tokenStatus.isStatus())
			return new ModelAndView("redirect:" + "/signin");

		return mv;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateUserRequestDto createUserRequestDto,
			HttpServletRequest request, HttpServletResponse response) {

		String email = createUserRequestDto.getEmail().length() == 0 ? null : createUserRequestDto.getEmail();

		createUserRequestDto.setEmail(email);

		TokenStatus tokenStatus = frontendService.isValidToken(request, response);

		if (tokenStatus != null && tokenStatus.isStatus()) {
			return new ResponseEntity<>("/", HttpStatus.OK);
		}

		CreateUserResponseStatus status = apiGatewayRequestUri.register(createUserRequestDto).getBody();
		if (!status.isStatus())
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

		ChangePasswordResponseStatus status = frontendService.changePassword(req.getPassword(), token);

		return new ResponseEntity<>(status.getMessage(), HttpStatus.OK);
	}

	@GetMapping("/signout-from-alldevices")
	public String signOutFromAllDevices(HttpServletRequest request, HttpServletResponse response) {

		TokenStatus tokenStatus = frontendService.isValidToken(request, response);

		if (tokenStatus == null || tokenStatus != null && !tokenStatus.isStatus())
			return "redirect:/signin";

		frontendService.removeAllTokens(request);

		return "redirect:/signin";
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

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView error(HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("error/error");

		return mv;
	}
}