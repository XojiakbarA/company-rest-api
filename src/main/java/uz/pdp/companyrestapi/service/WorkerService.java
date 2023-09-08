package uz.pdp.companyrestapi.service;

import uz.pdp.companyrestapi.dto.WorkerDTO;
import uz.pdp.companyrestapi.entity.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> findAll();
    Worker findById(Long id);
    Worker create(WorkerDTO dto);
    Worker update(WorkerDTO dto, Long id);
    Worker save(Worker worker);
    void deleteById(Long id);
    void setAttributes(WorkerDTO dto, Worker worker);
}
