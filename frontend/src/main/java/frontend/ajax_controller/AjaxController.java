package frontend.ajax_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/ajax")
@RestController
public class AjaxController {

    @GetMapping("/unauthorize-change-password")
    public ModelAndView ajaxUnauthorizePop(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/password-reset-token-invalid");
        return mv;
    }
    @RequestMapping(value = "/server-down", method = { RequestMethod.GET })
    public ModelAndView serverDown(HttpServletResponse response) throws IOException {
        ModelAndView mv= new ModelAndView();
        mv.setViewName("error/503-error");
        return mv;
    }

    @RequestMapping(value = "/confirmation-page", method = { RequestMethod.GET })
    public ModelAndView confirmationMailLink(HttpServletResponse response) throws IOException {
        ModelAndView mv= new ModelAndView();
        mv.setViewName("confirmation-email");
        return mv;
    }

    @GetMapping("/signin")
    public ModelAndView ajaxSigninPopupAfterResetPassword(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        //TokenStatus tokenStatus = frontendService.isValidToken(request, response);
        ModelAndView model = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

}
