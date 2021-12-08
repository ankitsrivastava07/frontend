package frontend.spring_security.filter;

import frontend.service.FrontendService;
import frontend.service.FrontendServiceImpl;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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


    @CacheEvict(cacheNames = {"session_Token","browser"},allEntries = true)
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request1= (HttpServletRequest)request;
      HttpServletResponse response1 = (HttpServletResponse)response;
        String uri=request1.getServletPath();
        Cookie cookies[]=request1.getCookies();
        String jwtToken=null;
        if(cookies!=null)
            for(Cookie cookie:cookies) {
                if (cookie.getName().equalsIgnoreCase("session_Token")) {
                    jwtToken=cookie.getValue();
                    isValidToken(cookie.getValue());
                    chain.doFilter(request, response);
                    return;
                }
            }
        if(jwtToken==null){
            FrontendServiceImpl frontendServiceImpl=(FrontendServiceImpl)frontendService;
            TenantContext.setTokenStatus(frontendServiceImpl.isValidTokenFallback(jwtToken,new Throwable()));
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isValidToken(String jwtToken){
        TokenStatus tokenStatus = frontendService.isValidToken(jwtToken);
        TenantContext.setTokenStatus(tokenStatus);
        return tokenStatus.isStatus();
    }
}
