package frontend.spring_security.config;

import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.spring_security.authentication_provider.AuthenticateUser;
import frontend.spring_security.filter.AuthenticationEntryPointFilter;
import frontend.spring_security.filter.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalAuthentication
public class SecureRequest extends WebSecurityConfigurerAdapter {
    @Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
    @Autowired private AuthenticateUser authenticateUser;
    @Autowired private AuthenticationEntryPointFilter authenticationEntryPointFilter;
    @Autowired JwtAccessTokenUtil jwtAccessTokenUtil;
    @Autowired
    FrontendService frontendService;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
        .addFilterBefore(new TokenValidatorFilter(apiGatewayRequestUri,jwtAccessTokenUtil,frontendService), BasicAuthenticationFilter.class)
        .cors().configurationSource(
                request ->{
                    CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authentication", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("Authentication"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
                    return configuration;
                })
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/home")
                .permitAll()
                .antMatchers("/register")
                .permitAll()
                .antMatchers("/check-connection").permitAll()
                .antMatchers("/signin").permitAll().and().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPointFilter).and().headers().cacheControl();
    }
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/ecommerce/**","/css/**","/images/**","/login-form/**","/response-popup/**","/**/*");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPointFilter authentionEntryPoint() throws Exception {
        return new AuthenticationEntryPointFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticateUser);
    }

   /* @Override
    public void configure(AuthenticationProvider authenticationProvider){
        authenticationProvider.authenticate(new UserCredentialRequest());
    }*/

}
