package uz.pdp.companyrestapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

@Data
public class DepartmentDTO {
    @NotBlank(message = "name is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "name must not be less than 3 and not more than 255")
    private String name;

    @NotNull(message = "companyId is required", groups = OnCreate.class)
    @Positive(message = "companyId must be a positive")
    private Long companyId;
}
