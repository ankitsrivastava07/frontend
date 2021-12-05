package frontend.spring_security.filter;


import frontend.constant.CookieNameConstant;
import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestRedirectFilter implements Filter {
    @Autowired
    private FrontendService frontendService;

    public RequestRedirectFilter(FrontendService frontendService){
        this.frontendService=frontendService;
    }

    @Override
    @CacheEvict(cacheNames = {"session_Token","browser"}, allEntries = true)
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest)request;
        HttpServletResponse response1=(HttpServletResponse) response;
        String jwtToken = request1.getHeader("Authentication");
        Cookie cookies[]=request1.getCookies();
        String url = request1.getServletPath();
        TokenStatus tokenStatus = null;
        if(cookies!=null)
            for(Cookie cookie:cookies) {
                if (cookie.getName().equals(CookieNameConstant.COOKIE_NAME) && !(tokenStatus=frontendService.isValidToken(cookie.getValue())).isStatus()) {
                    jwtToken = cookie.getValue();
                    TenantContext.setTokenStatus(tokenStatus);
                    ((HttpServletResponse) response).sendRedirect("/signin");
                    return;
                }
            }
        else if(tokenStatus==null){
            ((HttpServletResponse) response).sendRedirect("/signin");
            return;
        }
        TenantContext.setTokenStatus(tokenStatus);
       chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("Requset redirect destroy method called...");
    }
}
