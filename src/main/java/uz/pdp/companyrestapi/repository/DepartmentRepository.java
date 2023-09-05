package uz.pdp.companyrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.companyrestapi.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
