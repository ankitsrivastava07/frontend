package frontend.tenant;

import frontend.service.TokenStatus;

public class TenantContext {
    private transient static ThreadLocal<TokenStatus> tokenStatus = new ThreadLocal<>();

    public static void setTokenStatus(TokenStatus token) {
        tokenStatus.set(token);
    }

    public static TokenStatus getCurrentTokenStatus() {
        return tokenStatus.get();
    }
}
