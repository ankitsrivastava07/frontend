package frontend.service;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AddToCartCountProductsResponse {

    private Boolean status=Boolean.FALSE;
    private String  message;
    private Boolean isAccessTokenNewCreated;
    private String sessionToken;
    private HttpStatus httpStatus= HttpStatus.OK;
    private Integer productCount=0;
}
