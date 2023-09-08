package uz.pdp.companyrestapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyrestapi.dto.AddressDTO;
import uz.pdp.companyrestapi.dto.WorkerDTO;
import uz.pdp.companyrestapi.entity.Address;
import uz.pdp.companyrestapi.entity.Department;
import uz.pdp.companyrestapi.entity.Worker;
import uz.pdp.companyrestapi.exception.ResourceExistsException;
import uz.pdp.companyrestapi.exception.ResourceNotFoundException;
import uz.pdp.companyrestapi.repository.WorkerRepository;
import uz.pdp.companyrestapi.service.AddressService;
import uz.pdp.companyrestapi.service.DepartmentService;
import uz.pdp.companyrestapi.service.WorkerService;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AddressService addressService;

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Override
    public Worker findById(Long id) {
        return workerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Worker.class.getSimpleName(), id)
        );
    }

    @Override
    public Worker create(WorkerDTO dto) {
        if (workerRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new ResourceExistsException("A worker with phoneNumber = " + dto.getPhoneNumber() + " already exists");
        }
        Worker worker = new Worker();

        if (dto.getAddress() != null) {
            AddressDTO addressDTO = dto.getAddress();
            Address address = new Address();
            addressService.setAttributes(addressDTO, address);
            worker.setAddress(address);
        }
        setAttributes(dto, worker);

        return save(worker);
    }

    @Override
    public Worker update(WorkerDTO dto, Long id) {
        if (workerRepository.existsByPhoneNumberAndIdNot(dto.getPhoneNumber(), id)) {
            throw new ResourceExistsException("A worker with phoneNumber = " + dto.getPhoneNumber() + " already exists");
        }
        Worker worker = findById(id);

        if (dto.getAddress() != null) {
            Address address = worker.getAddress();
            AddressDTO addressDTO = dto.getAddress();
            addressService.setAttributes(addressDTO, address);
        }
        setAttributes(dto, worker);

        return save(worker);
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public void deleteById(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new ResourceNotFoundException(Worker.class.getSimpleName(), id);
        }
        workerRepository.deleteById(id);
    }

    @Override
    public void setAttributes(WorkerDTO dto, Worker worker) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            worker.setName(dto.getName());
        }
        if (dto.getPhoneNumber() != null) {
            worker.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getDepartmentId() != null) {
            Department department = departmentService.findById(dto.getDepartmentId());
            worker.setDepartment(department);
        }
    }
}
