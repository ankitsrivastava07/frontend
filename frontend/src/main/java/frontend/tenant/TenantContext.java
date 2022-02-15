package frontend.tenant;
import frontend.service.TokenStatus;
public class TenantContext {
    private static ThreadLocal<TokenStatus> tokenStatus = null;

    public static void setTokenStatus(TokenStatus token) {
        if(tokenStatus==null){
          tokenStatus= new ThreadLocal<>();
        }
        tokenStatus.set(token);
    }

    public static TokenStatus getCurrentTokenStatus() {
        return tokenStatus!=null? tokenStatus.get() : null;
    }

    public static void remove() {
        if(tokenStatus!=null) {
         tokenStatus.remove();
            System.out.println("Removed data From Thread Local ");
        }
    }
}
