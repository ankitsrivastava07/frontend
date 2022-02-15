package frontend.spring_security.filter;
import frontend.constant.ResponseConstant;
import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.tenant.TenantContext;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@Order(2)
public class TokenValidatorFilter implements Filter {
    @Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
    @Autowired private JwtAccessTokenUtil jwtAccessTokenUtil;
    @Autowired private FrontendService frontendService;
    public TokenValidatorFilter(ApiGatewayRequestUri apiGatewayRequestUri,JwtAccessTokenUtil jwtAccessTokenUtil,FrontendService frontendService) {
        this.apiGatewayRequestUri = apiGatewayRequestUri;
        this.jwtAccessTokenUtil= jwtAccessTokenUtil;
        this.frontendService=frontendService;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request1= (HttpServletRequest)request;
    HttpServletResponse response1= (HttpServletResponse)response;
    String jwtToken = request1.getHeader("AuthToken");
    String uri=request1.getServletPath();
    TokenStatus tokenStatus = null;
    if(!uri.equalsIgnoreCase("/api/v1/user/register") && !uri.equalsIgnoreCase("/api/v1/user/change-password") && !uri.equalsIgnoreCase("/api/v1/user/login") && !uri.equals("/api/v1/user/userName-check")) {
        if(jwtToken==null || !(tokenStatus=frontendService.isValidToken(jwtToken)).isStatus()) {
            response1.setContentType("application/json");
            PrintWriter writer = response1.getWriter();
            Map<String, Object> map = new HashMap<>();
            map.put("status", Boolean.FALSE);
            map.put("message", ResponseConstant.SESSION_EXPIRED_DEFAULT_MESSAGE);
            map.put("errorCode", HttpStatus.UNAUTHORIZED.name()+"  Request");
            map.put("isSessionExpired", Boolean.TRUE);
            map.put("redirectURL", "/signin");
            JSONObject jsonObject = new JSONObject(map);
            writer.print(jsonObject.toString());
            response1.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
    }
    TenantContext.setTokenStatus(tokenStatus);
    chain.doFilter(request,response);
  }
    public void destroy(){
        System.out.println("Destroyed method called");
    }
}

