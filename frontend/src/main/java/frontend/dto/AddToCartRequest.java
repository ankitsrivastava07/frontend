package frontend.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String token;
    private Long productId;
    private Long subcategoryId;
    private Double subTotal;
    private Double grandTotal;
    private Long discountId;
    private Boolean status;
}
