package frontend.spring_security.config;
import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.spring_security.authentication_provider.AuthenticateUser;
import frontend.spring_security.filter.AuthenticationEntryPointFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalAuthentication
//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity(debug = true)
public class SecureRequest extends WebSecurityConfigurerAdapter {
    @Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
    @Autowired private AuthenticateUser authenticateUser;
    @Autowired private AuthenticationEntryPointFilter authenticationEntryPointFilter;
    @Autowired JwtAccessTokenUtil jwtAccessTokenUtil;
    @Autowired
    FrontendService frontendService;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPointFilter)
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/popup").permitAll()
                .antMatchers("/signout").permitAll()
                .antMatchers("/user/validate").permitAll()
                .antMatchers("/signout-from-all-devices")
                .permitAll()
                .antMatchers("/sign-up","/forget-password")
                .permitAll()
                .antMatchers("/check-connection").permitAll()
                .antMatchers("/ajax/*").permitAll()
                .antMatchers("/user/profile").permitAll()
                .antMatchers("/signin").permitAll()
                .antMatchers("/api/v1/user/*" ,"/api/v1/user/profile/*")
                .permitAll()
                .antMatchers("/change-password","/change-password?code=")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().disable().csrf().disable();
                 httpSecurity.authenticationProvider(authenticateUser);
                 httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPointFilter).and().headers().cacheControl();
    }
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().
         antMatchers( "/images/**","/css/**","/ecommerce/**","/login-form/**","/register-form/**","/response-popup/css/**","/update/**");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPointFilter authentionEntryPoint() throws Exception {
        return new AuthenticationEntryPointFilter();
    }

   /* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticateUser);
    }
*/
   /* @Override
    public void configure(AuthenticationProvider authenticationProvider){
        authenticationProvider.authenticate(new UserCredentialRequest());
    }*/
}
