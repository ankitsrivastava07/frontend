package frontend;

import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.spring_security.filter.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients("frontend.*")
public class FrontendApplication {
@Autowired private ApiGatewayRequestUri apiGatewayRequestUri;
     @Autowired
	JwtAccessTokenUtil jwtAccessTokenUtil;
	 @Autowired
	FrontendService frontendService;
	public FrontendApplication(ApiGatewayRequestUri apiGatewayRequestUri){
		this.apiGatewayRequestUri=apiGatewayRequestUri;
	}

	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TokenValidatorFilter(apiGatewayRequestUri,jwtAccessTokenUtil,frontendService));
		registrationBean.addUrlPatterns("/change-password","/orders","/signout-from-all-devices","/signout","/account");
		return registrationBean;
	}
}
