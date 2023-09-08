package uz.pdp.companyrestapi.service;

import uz.pdp.companyrestapi.dto.CompanyDTO;
import uz.pdp.companyrestapi.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findById(Long id);
    Company create(CompanyDTO dto);
    Company update(CompanyDTO dto, Long id);
    Company save(Company company);
    void deleteById(Long id);
    void setAttributes(CompanyDTO dto, Company company);
}
