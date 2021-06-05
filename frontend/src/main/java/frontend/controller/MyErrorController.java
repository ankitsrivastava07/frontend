
package frontend.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {

			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				ModelAndView mv = new ModelAndView("forward:" + "/error");
				mv.setViewName("error/error-404");
				return mv;
			} else if (statusCode == HttpStatus.TOO_MANY_REQUESTS.value())
				return new ModelAndView("error/error-429");

			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

				ModelAndView mv = new ModelAndView("forward:" + "/error");
				mv.setViewName("error/error-500");
				return mv;
			}
		}
		return new ModelAndView("forward:" + "error/error");
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
