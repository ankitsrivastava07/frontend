package frontend.response;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AddToCartResponse {
    private String sessionToken;
	private Boolean status;
    private String message;
    private Boolean isAccessTokenNewCreated;
	private LocalDateTime createdAt;
    private Long totalProduct;
}
