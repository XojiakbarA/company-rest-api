package uz.pdp.companyrestapi.service;

import uz.pdp.companyrestapi.dto.DepartmentDTO;
import uz.pdp.companyrestapi.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    Department create(DepartmentDTO dto);
    Department update(DepartmentDTO dto, Long id);
    Department save(Department department);
    void deleteById(Long id);
    void setAttributes(DepartmentDTO dto, Department department);
}
