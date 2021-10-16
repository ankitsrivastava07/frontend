package frontend.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class OrderDto {
    @NotNull
    @Pattern(regexp = "\\d+")
    private Long productId;
    @NotNull
    @Pattern(regexp = "\\d+")
    private Long subCategoryId;
    @NotNull
    @Pattern(regexp = "[0-9]{1,13}(\\.[0-9]*)?")
    private Double price;
    @NotNull
    @Pattern(regexp = "\\d+")
    private Long discountId;
}
