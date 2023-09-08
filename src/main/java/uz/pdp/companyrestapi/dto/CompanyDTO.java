package uz.pdp.companyrestapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

@Data
public class CompanyDTO {
    @NotBlank(message = "corpName is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "corpName must not be less than 3 and not more than 255")
    private String corpName;

    @NotBlank(message = "directorName is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "directorName must not be less than 3 and not more than 255")
    private String directorName;

    @Valid
    @NotNull(message = "address is required", groups = OnCreate.class)
    private AddressDTO address;
}
