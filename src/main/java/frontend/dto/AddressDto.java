package frontend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @NotNull(message = "Please enter valid address")
    private String addressLine1;
   @NotNull(message = "Please enter valid land mark")
    private String landMark;
    @NotNull(message = "Please enter valid pincode")
    private String pinCode;
    @NotNull(message = "Please enter valid city")
    private Long cityId;
    @NotNull(message = "Please enter valid state")
    private Long stateId;
    @NotNull(message = "Please enter valid country")
    private Long countryId;
    @NotNull(message = "Please enter valid first name")
    private String firstName;
    @NotNull(message = "Please enter valid last name")
    private String lastName;

}
