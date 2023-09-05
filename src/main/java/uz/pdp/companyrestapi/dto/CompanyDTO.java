package uz.pdp.companyrestapi.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private String corpName;
    private String directorName;
    private AddressDTO address;
}
