package uz.pdp.companyrestapi.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.companyrestapi.dto.AddressDTO;
import uz.pdp.companyrestapi.entity.Address;
import uz.pdp.companyrestapi.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public void setAttributes(AddressDTO dto, Address address) {
        if (dto.getStreet() != null && !dto.getStreet().isBlank()) {
            address.setStreet(dto.getStreet());
        }
        if (dto.getHomeNumber() != null && !dto.getHomeNumber().isBlank()) {
            address.setHomeNumber(dto.getHomeNumber());
        }
    }
}
