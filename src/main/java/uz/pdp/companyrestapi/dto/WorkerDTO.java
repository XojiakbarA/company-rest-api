package uz.pdp.companyrestapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

@Data
public class WorkerDTO {
    @NotBlank(message = "name is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "name must not be less than 3 and not more than 255")
    private String name;

    @Min(value = 100_000_000_000L, message = "phoneNumber must be 12 digits")
    @Max(value = 999_999_999_999L, message = "phoneNumber must be 12 digits")
    private Long phoneNumber;

    @NotNull(message = "departmentId is required", groups = OnCreate.class)
    @Positive(message = "departmentId must be a positive")
    private Long departmentId;

    @Valid
    @NotNull(message = "address is required", groups = OnCreate.class)
    private AddressDTO address;
}
