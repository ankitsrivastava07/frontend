package frontend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddToCartResponse {

    private String sessionToken;
    private Boolean status;
    private String message;
    private Boolean isAccessTokenNewCreated;
    private LocalDateTime createdAt;
    private Long totalProduct;
}
