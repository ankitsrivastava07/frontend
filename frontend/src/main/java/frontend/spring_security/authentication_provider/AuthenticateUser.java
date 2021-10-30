package frontend.spring_security.authentication_provider;

import frontend.api.request.UserCredentialRequest;
import frontend.controller.LoginStatus;
import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthenticateUser implements AuthenticationProvider {
    @Autowired private FrontendService frontendService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailOrMobile=authentication.getPrincipal().toString();
        String password=authentication.getCredentials().toString();
        UserCredentialRequest userCredentialRequest = new UserCredentialRequest();
        userCredentialRequest.setEmailOrMobile(emailOrMobile);
        userCredentialRequest.setPassword(password);
        LoginStatus loginStatus= frontendService.createAuthenticationToken(userCredentialRequest);
         if (loginStatus.isStatus())
             return new UsernamePasswordAuthenticationToken(emailOrMobile,password,new ArrayList<>());
         throw new BadCredentialsException("Invalid email/mobile and password");
         }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
