package uz.pdp.companyrestapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestapi.Response;
import uz.pdp.companyrestapi.dto.CompanyDTO;
import uz.pdp.companyrestapi.entity.Company;
import uz.pdp.companyrestapi.service.CompanyService;
import uz.pdp.companyrestapi.validator.marker.OnCreate;

import java.util.List;

@Validated
@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Company> companies = companyService.findAll();

        Response response = new Response(companies, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Company company = companyService.findById(id);

        Response response = new Response(company, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody CompanyDTO dto) {
        Company company = companyService.create(dto);

        Response response = new Response(company, HttpStatus.CREATED.name());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody CompanyDTO dto, @PathVariable Long id) {
        Company company = companyService.update(dto, id);

        Response response = new Response(company, HttpStatus.OK.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        companyService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
