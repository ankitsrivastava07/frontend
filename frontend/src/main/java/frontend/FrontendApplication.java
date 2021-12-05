package frontend;

import frontend.service.ApiGatewayRequestUri;
import frontend.service.FrontendService;
import frontend.session_validator.JwtAccessTokenUtil;
import frontend.spring_security.filter.RequestRedirectFilter;
import frontend.spring_security.filter.TokenValidatorFilter;
import frontend.spring_security.filter.WebControllerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients("frontend.*")
@EnableCaching
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
		SpringApplication.run(FrontendApplication.class, args);}

	@Bean
	public FilterRegistrationBean tokenValidatorFilter() {
	FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
	registrationBean.setFilter(new TokenValidatorFilter(apiGatewayRequestUri, jwtAccessTokenUtil, frontendService));
	registrationBean.addUrlPatterns("/api/*");
	return registrationBean;
	}

	@Bean
	public FilterRegistrationBean requestRedirectFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new RequestRedirectFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/user/*");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean webControllerFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebControllerFilter(frontendService));
		filterRegistrationBean.addUrlPatterns("/","/home","/signin","/signout","/register","/popup","/forget-password");
		return filterRegistrationBean;
	}
}
