package frontend.spring_security.filter;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        if(cookies==null){
            TenantContext.setTokenStatus(null);
        }
        if(cookies==null && !uri.equals("/") && !uri.equals("/register") && !uri.equals("/forget-password") && !uri.equals("/signin")){
            ((HttpServletResponse) response).sendRedirect("/signin");
            return;
        }
        List<String> list=null;
        if(cookies!=null) {
           list = Arrays.asList(cookies).stream().filter(cookie -> cookie.getName().equalsIgnoreCase("session_Token")).map(cookie -> cookie.getValue()).collect(Collectors.toList());
           if(list.size()>0)
           isValidToken(list.get(0));
        }
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("session_Token") && !uri.equals("/register") && !uri.equals("/") && !uri.equals("/forget-password") && !uri.equals("/signin") && list.size()>0 && !isValidToken(cookie.getValue())) {
                    jwtToken = cookie.getValue();
                        ((HttpServletResponse) response).sendRedirect("/signin");
                        return;
                } else if(list.isEmpty() && !uri.equals("/signin") && !uri.equals("/register") && !uri.equals("/") && !uri.equals("/forget-password") && !uri.equals("/signin")){
                    ((HttpServletResponse) response).sendRedirect("/signin");
                    return;
                }
            }
        }
    if(cookies!=null && Arrays.asList(cookies).stream().filter(cookie->cookie.getName().equalsIgnoreCase("session_Token")).map(cookie->cookie.getValue()).findFirst().isPresent() && (jwtToken=Arrays.asList(cookies).stream().filter(cookie->cookie.getName().equalsIgnoreCase("session_Token")).map(cookie->cookie.getValue()).findFirst().get())!=null && isValidToken(jwtToken) && uri.equalsIgnoreCase("/register") && uri.equals("/signin") && uri.equalsIgnoreCase("/forget-password")){
            response1.sendRedirect("/");
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

    public TokenStatus defaultMessage(String message) {
        TokenStatus tokenStatus = new TokenStatus();
        tokenStatus.setMessage(message);
        TenantContext.setTokenStatus(tokenStatus);
        return tokenStatus;
    }

}
