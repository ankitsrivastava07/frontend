package frontend.spring_security.filter;


import frontend.service.FrontendService;
import frontend.service.TokenStatus;
import frontend.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest)request;
        HttpServletResponse response1=(HttpServletResponse) response;
        String jwtToken = request1.getHeader("Authentication");
        Cookie cookies[]=request1.getCookies();
        String url = request1.getServletPath();
        if(cookies!=null)
            for(Cookie cookie:cookies)
                if(cookie.getName().equalsIgnoreCase("session_Token")) {
                    jwtToken=cookie.getValue();TokenStatus tokenStatus = frontendService.isValidToken(jwtToken);
                    TenantContext.setTokenStatus(tokenStatus);
                    //chain.doFilter(request,response);
                   // return;
                }
        if(jwtToken==null) {
            response1.sendRedirect("/signin");
            return;
        }
       chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("Requset redirect destroy method called...");
    }
}
