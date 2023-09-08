package uz.pdp.companyrestapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.companyrestapi.dto.AddressDTO;
import uz.pdp.companyrestapi.dto.CompanyDTO;
import uz.pdp.companyrestapi.entity.Address;
import uz.pdp.companyrestapi.entity.Company;
import uz.pdp.companyrestapi.exception.ResourceExistsException;
import uz.pdp.companyrestapi.exception.ResourceNotFoundException;
import uz.pdp.companyrestapi.repository.CompanyRepository;
import uz.pdp.companyrestapi.service.AddressService;
import uz.pdp.companyrestapi.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AddressService addressService;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Company.class.getSimpleName(), id)
        );
    }

    @Transactional
    @Override
    public Company create(CompanyDTO dto) {
        if (companyRepository.existsByCorpName(dto.getCorpName())) {
            throw new ResourceExistsException("A company with corpName = " + dto.getCorpName() + " already exists");
        }
        Company company = new Company();

        if (dto.getAddress() != null) {
            AddressDTO addressDTO = dto.getAddress();
            Address address = new Address();
            addressService.setAttributes(addressDTO, address);
            company.setAddress(address);
        }
        setAttributes(dto, company);

        return save(company);
    }

    @Transactional
    @Override
    public Company update(CompanyDTO dto, Long id) {
        if (companyRepository.existsByCorpNameAndIdNot(dto.getCorpName(), id)) {
            throw new ResourceExistsException("A company with corpName = " + dto.getCorpName() + " already exists");
        }
        Company company = findById(id);

        if (dto.getAddress() != null) {
            Address address = company.getAddress();
            AddressDTO addressDTO = dto.getAddress();
            addressService.setAttributes(addressDTO, address);
        }
        setAttributes(dto, company);

        return save(company);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException(Company.class.getSimpleName(), id);
        }
        companyRepository.deleteById(id);
    }

    @Override
    public void setAttributes(CompanyDTO dto, Company company) {
        if (dto.getCorpName() != null && !dto.getCorpName().isBlank()) {
            company.setCorpName(dto.getCorpName());
        }
        if (dto.getDirectorName() != null && !dto.getDirectorName().isBlank()) {
            company.setDirectorName(dto.getDirectorName());
        }
    }
}
