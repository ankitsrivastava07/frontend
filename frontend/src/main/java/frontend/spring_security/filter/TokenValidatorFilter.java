package frontend.spring_security.filter;

import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.tenant.TenantContext;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class TokenValidatorFilter extends OncePerRequestFilter {

    @Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
    @Autowired private JwtAccessTokenUtil jwtAccessTokenUtil;
    @Autowired private FrontendService frontendService;

    public TokenValidatorFilter(ApiGatewayRequestUri apiGatewayRequestUri,JwtAccessTokenUtil jwtAccessTokenUtil,FrontendService frontendService) {
        this.apiGatewayRequestUri = apiGatewayRequestUri;
        this.jwtAccessTokenUtil= jwtAccessTokenUtil;
        this.frontendService=frontendService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         Cookie cookies[]=request.getCookies();
         String session_Token=request.getHeader("session_Token");
         if(cookies==null && !request.getServletPath().equals("/") && !request.getServletPath().equals("/register")) {
             response.sendRedirect("/signin");
             return;
         }
         if(cookies!=null)
         for(Cookie cookie:cookies)
             if(cookie.getName().equalsIgnoreCase("session_Token")) {
                 session_Token = cookie.getValue();
                 if (!isValidToken(session_Token,request) && !request.getServletPath().equals("/")  && !request.getServletPath().equals("/register")) {
                     response.sendRedirect("/signin");
                     return;
                 }
             }
        filterChain.doFilter(request,response);
    }
    @Override
    public boolean shouldNotFilter(HttpServletRequest request){
        String url="/users/profile/edit";
        String urlReq=request.getServletPath();
        boolean flag= urlReq.equalsIgnoreCase("/signin") || urlReq.equalsIgnoreCase("/signout") || request.getServletPath().equals("/check-connection") || request.getServletPath().equals("/contact");
        return flag;
    }

    private boolean isValidToken(String authenticationToken,HttpServletRequest request){
        TokenStatus tokenStatus=frontendService.isValidToken(authenticationToken);
        if (tokenStatus.isStatus()) {
            TenantContext.setTokenStatus(tokenStatus);
            return true;
        }
        else if (!tokenStatus.isStatus()) {
            TenantContext.setTokenStatus(tokenStatus);
            return false;
        }
        return false;
    }
}
