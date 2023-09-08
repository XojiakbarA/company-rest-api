package uz.pdp.companyrestapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestapi.Response;
import uz.pdp.companyrestapi.dto.DepartmentDTO;
import uz.pdp.companyrestapi.entity.Department;
import uz.pdp.companyrestapi.service.DepartmentService;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

import java.util.List;

@Validated
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Department> departments = departmentService.findAll();

        Response response = new Response(departments, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Department department = departmentService.findById(id);

        Response response = new Response(department, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody DepartmentDTO dto) {
        Department department = departmentService.create(dto);

        Response response = new Response(department, HttpStatus.CREATED.name());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody DepartmentDTO dto, @PathVariable Long id) {
        Department department = departmentService.update(dto, id);

        Response response = new Response(department, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        departmentService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
