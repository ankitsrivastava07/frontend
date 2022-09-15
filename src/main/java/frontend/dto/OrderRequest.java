package frontend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Please enter userId")
    private Long userId;
    @NotNull(message = "Please enter orderDto")
    private List<OrderDto> orderDto;
    @NotNull(message = "Please enter grandTotal amount")
    private Double grandTotal;
    @NotNull(message = "Please enter order type ")
    private String orderType;
    @NotNull(message = "Please enter address before proceed")
    private AddressDto addressDto;
}
