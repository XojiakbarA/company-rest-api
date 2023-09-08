package uz.pdp.companyrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.companyrestapi.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Long id);
}
