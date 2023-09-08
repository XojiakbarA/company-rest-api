package uz.pdp.companyrestapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyrestapi.dto.DepartmentDTO;
import uz.pdp.companyrestapi.entity.Company;
import uz.pdp.companyrestapi.entity.Department;
import uz.pdp.companyrestapi.exception.ResourceExistsException;
import uz.pdp.companyrestapi.exception.ResourceNotFoundException;
import uz.pdp.companyrestapi.repository.DepartmentRepository;
import uz.pdp.companyrestapi.service.CompanyService;
import uz.pdp.companyrestapi.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyService companyService;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Department.class.getSimpleName(), id)
        );
    }

    @Override
    public Department create(DepartmentDTO dto) {
        if (departmentRepository.existsByNameAndCompanyId(dto.getName(), dto.getCompanyId())) {
            throw new ResourceExistsException("A department with name = " + dto.getName() + " and company_id = " + dto.getCompanyId() + " already exists");
        }
        Department department = new Department();

        setAttributes(dto, department);

        return save(department);
    }

    @Override
    public Department update(DepartmentDTO dto, Long id) {
        if (departmentRepository.existsByNameAndCompanyIdAndIdNot(dto.getName(), dto.getCompanyId(), id)) {
            throw new ResourceExistsException("A department with name = " + dto.getName() + " and company_id = " + dto.getCompanyId() + " already exists");
        }
        Department department = findById(id);

        setAttributes(dto, department);

        return save(department);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(Department.class.getSimpleName(), id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public void setAttributes(DepartmentDTO dto, Department department) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            department.setName(dto.getName());
        }
        if (dto.getCompanyId() != null) {
            Company company = companyService.findById(dto.getCompanyId());
            department.setCompany(company);
        }
    }
}
