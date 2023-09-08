package uz.pdp.companyrestapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestapi.Response;
import uz.pdp.companyrestapi.dto.WorkerDTO;
import uz.pdp.companyrestapi.entity.Worker;
import uz.pdp.companyrestapi.service.WorkerService;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

import java.util.List;

@Validated
@RestController
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Worker> workers = workerService.findAll();

        Response response = new Response(workers, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Worker worker = workerService.findById(id);

        Response response = new Response(worker, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody WorkerDTO dto) {
        Worker worker = workerService.create(dto);

        Response response = new Response(worker, HttpStatus.CREATED.name());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody WorkerDTO dto, @PathVariable Long id) {
        Worker worker = workerService.update(dto, id);

        Response response = new Response(worker, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        workerService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
