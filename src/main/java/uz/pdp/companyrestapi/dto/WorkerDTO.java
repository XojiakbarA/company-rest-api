package uz.pdp.companyrestapi.dto;

import lombok.Data;

@Data
public class WorkerDTO {
    private String name;
    private Integer phoneNumber;
    private Long departmentId;
    private AddressDTO address;
}
