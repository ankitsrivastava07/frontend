package frontend.spring_security.filter;
import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    String jwtToken = request1.getHeader("Authentication");
    String uri=request1.getServletPath();
    TokenStatus tokenStatus = frontendService.isValidToken(jwtToken);
    if(!tokenStatus.isStatus()) {
        response1.sendError(401);
        return;
    }
    TenantContext.setTokenStatus(tokenStatus);
    chain.doFilter(request,response);
  }
    public void destroy(){
        System.out.println("Destroyed method called");
    }
}

