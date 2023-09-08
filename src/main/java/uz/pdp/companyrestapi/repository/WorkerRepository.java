package uz.pdp.companyrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.companyrestapi.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhoneNumber(Long phoneNumber);
    boolean existsByPhoneNumberAndIdNot(Long phoneNumber, Long id);
}
