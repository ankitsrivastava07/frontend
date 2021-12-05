package frontend.spring_security.filter;

import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebControllerFilter implements Filter {

    @Autowired
    FrontendService frontendService;

    public WebControllerFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request1= (HttpServletRequest)request;
      HttpServletResponse response1 = (HttpServletResponse)response;
        String jwtToken=request1.getHeader("Authentication");
        String uri=request1.getServletPath();
        Cookie cookies[]=request1.getCookies();
        /*if(jwtToken!=null) {
            TokenStatus tokenStatus = frontendService.isValidToken(jwtToken);
            TenantContext.setTokenStatus(tokenStatus);
            chain.doFilter(request,response);
            return;
        }*/
        if(cookies!=null)
            for(Cookie cookie:cookies)
                if(cookie.getName().equalsIgnoreCase("session_Token")) {
                    TokenStatus tokenStatus = frontendService.isValidToken(cookie.getValue());
                    TenantContext.setTokenStatus(tokenStatus);
                    chain.doFilter(request,response);
                    return;
                }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
