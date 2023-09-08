package uz.pdp.companyrestapi.service;

import uz.pdp.companyrestapi.dto.AddressDTO;
import uz.pdp.companyrestapi.entity.Address;

public interface AddressService {
    void setAttributes(AddressDTO dto, Address address);
}
