package frontend.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    
    private String orderStatus;
    private Boolean status;
    private String orderId;
    private Boolean isNewTokenCreated=Boolean.FALSE;
    private String accessToken;
    private String message;
    private Integer httpStatus=200;
    private String path="";
}
