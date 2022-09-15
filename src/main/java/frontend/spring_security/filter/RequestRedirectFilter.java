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
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse) res;
        String jwtToken = "";
        Cookie cookies[]=request1.getCookies();
        String url = request1.getServletPath();
        int status=response.getStatus();
        TokenStatus tokenStatus = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieNameConstant.COOKIE_NAME) && !(tokenStatus = frontendService.isValidToken(cookie.getValue())).isStatus()) {
                    jwtToken = cookie.getValue();
                    TenantContext.setTokenStatus(tokenStatus);
                    ((HttpServletResponse) res).sendRedirect("/signin");
                    return;
                }
            }
        }
        if(!url.equalsIgnoreCase("/api/v1/user/login") && tokenStatus==null){
            response.sendRedirect("/signin");
            return;
        }
        TenantContext.setTokenStatus(tokenStatus);
       chain.doFilter(request1,response);
    }

    @Override
    public void destroy() {
        System.out.println("Requset redirect destroy method called...");
    }
}
