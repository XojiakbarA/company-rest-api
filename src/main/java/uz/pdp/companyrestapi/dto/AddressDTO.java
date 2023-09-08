package uz.pdp.companyrestapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

@Data
public class AddressDTO {
    @NotBlank(message = "street is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "street must not be less than 3 and not more than 255")
    private String street;

    @NotBlank(message = "homeNumber is required", groups = OnCreate.class)
    @Size(min = 1, max = 255, message = "homeNumber must not be less than 3 and not more than 255")
    private String homeNumber;
}
