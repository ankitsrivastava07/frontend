package frontend.service;

import lombok.Data;

@Data
public class AddToCartCountProductsResponse {

    private Boolean status=Boolean.FALSE;
    private String  message="Successfully fetched response";
    private Integer httpStatus;
    private Integer productCount=0;
}
