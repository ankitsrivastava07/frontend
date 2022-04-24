package frontend.feign;
import frontend.api.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book",url = "http://localhost:8765/book")
public interface BookProxy {

    @GetMapping("/{query}")
    ResponseEntity<ApiResponse> searchByQuery(@PathVariable String query);
}

