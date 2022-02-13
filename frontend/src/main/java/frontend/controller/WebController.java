package frontend.controller;
import frontend.api.dto.response.UserDto;
import frontend.constant.ResponseConstant;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("")
public class WebController {
    @Autowired
    private FrontendService frontendService;

    @GetMapping({"/", "", "/home"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
        TokenStatus tokenStatus=TenantContext.getCurrentTokenStatus();
        ModelAndView model = new ModelAndView();
        model.addObject("userName","");
        if(tokenStatus!=null && tokenStatus.isStatus())
            model.addObject("userName",tokenStatus.getFirstName());
        model.setViewName("index");
        return model;
    }

    @GetMapping({"/contact"})
    public ModelAndView contact(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.setViewName("contact-us");
        return model;
    }

    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public void redirect(@RequestParam("redirect") String redirectUrl, HttpServletResponse response) throws IOException {
        response.sendRedirect(redirectUrl);
        return;
    }

    @GetMapping("/signin")
    public ModelAndView signin(@RequestHeader(name = "Authentication",required = false)String authentication,@RequestHeader(value = "browser",required = false)String browser,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        TokenStatus tokenStatus=TenantContext.getCurrentTokenStatus();
        if (tokenStatus != null && tokenStatus.isStatus()) {
            ModelAndView model = new ModelAndView();
            model.setViewName("redirect:/");
            return model;
        }
        if (tokenStatus != null && !tokenStatus.isStatus()) {
            mv.addObject("message", tokenStatus.getMessage());
            return mv;
        }
        return mv;
    }

    @GetMapping("/signout")
    public void signout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        TokenStatus tokenStatus= TenantContext.getCurrentTokenStatus();
        if(tokenStatus!=null)
        frontendService.invalidateToken(tokenStatus.getAccessToken());
        response.sendRedirect("/signin");
    }

    @GetMapping("/register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sign-up");
        TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
        if (tokenStatus != null && tokenStatus.isStatus())
            return new ModelAndView("redirect:" + "/");
        return mv;
    }

    @GetMapping("/change-password")
    public ModelAndView changePasswod(@RequestParam(name = "code") String code, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String jwtToken= frontendService.getToken(request);
        if(jwtToken!=null && frontendService.isValidToken(jwtToken).isStatus())
            return new ModelAndView("redirect:" + "/");
        else if(code==null)
            return new ModelAndView("redirect:" + "/signin");
        ResponseConstant responseConstant = frontendService.authenticateIdentityToken(code);
        if (!responseConstant.getStatus()) {
            mv.setViewName("token_valid");
            mv.addObject("message", responseConstant.getMessage());
            return mv;
        }
        mv.setViewName("change-password");
        return mv;
    }

    @GetMapping("/signout-from-all-devices")
    public void signOutFromAllDevices(HttpServletResponse response) throws IOException {
        TokenStatus tokenStatus=TenantContext.getCurrentTokenStatus();
        if(tokenStatus.isStatus())
            frontendService.removeAllTokens(tokenStatus);
        response.sendRedirect("/signin");
    }

    @RequestMapping("*/error")
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        ModelAndView mv = new ModelAndView("forward:" + "/");
        mv.setViewName("error/error-500");
        return mv;
    }

    @RequestMapping("/popup")
    public ModelAndView responsePopUp() {
        TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
        ModelAndView mv = new ModelAndView();
        if (tokenStatus != null && !tokenStatus.isStatus())
            return new ModelAndView("redirect:" + "/signin");
        mv.setViewName("/response-html/popup");
        return mv;
    }

    @GetMapping("/check-connection")
    public ResponseEntity<?> checkConnection() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public ModelAndView redirectToUrl(@RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forget-password");
        return mv;
    }

    @GetMapping("/user/profile")
    public ModelAndView profile() {
        TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("profile");
        UserDto userDto= null;
        if (tokenStatus!=null && tokenStatus.isStatus()){
            userDto=frontendService.profile(tokenStatus.getAccessToken(),tokenStatus.getBrowser());
            mv.addObject("userDto",userDto);
            mv.addObject("userName",userDto.getFirstName());
            String encoded="";
            if(userDto.getS3BucketFileURL()!=null)
                encoded =userDto.getS3BucketFileURL();

            mv.addObject("fileStream",encoded);
            if(userDto.getAddress()==null)
                mv.addObject("address","");
            else
                mv.addObject("address",userDto.getAddress().trim());

            if(userDto.getHttpStatus()==503) {
                mv.addObject("userDto", "");
                mv.addObject("serviceStatus", "503");
            }
            return mv;
        }
        return mv;
    }

    @GetMapping(path = "/user/order")
    public ModelAndView order() {
        TokenStatus tokenStatus=TenantContext.getCurrentTokenStatus();
        if (!tokenStatus.isStatus())
            return new ModelAndView("redirect:" + "/signin");
        return new ModelAndView("redirect:" + "/");
    }

    @GetMapping(path = "/user/auth")
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

    @GetMapping("/forget-password")
    public ModelAndView forgetPassword(@RequestParam(name = "code", required = false) String code, HttpServletRequest request, HttpServletResponse response) {
        TokenStatus tokenStatus = TenantContext.getCurrentTokenStatus();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reset-password");
        return mv;
    }

}
