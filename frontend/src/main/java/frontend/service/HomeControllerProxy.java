package frontend.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "home-controller", url = "localhost:8080",primary = false)
public interface HomeControllerProxy {

	@GetMapping("/")
	void home();

}
