package uz.pdp.companyrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyrestapi.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
