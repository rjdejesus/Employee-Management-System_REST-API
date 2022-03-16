package com.rjdejesus.ems.restfulwebservice.EMSRestService.employee;

import com.rjdejesus.ems.restfulwebservice.EMSRestService.exception.EmployeeNotFoundException;
import com.rjdejesus.ems.restfulwebservice.EMSRestService.exception.IncompleteEmployeeNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // LIST ALL
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // LIST ALL w/ Pagination
    public Page<Employee> getAllEmployees(int offset, int size) {
        return repository.findAll(PageRequest.of(offset, size));
    }

    // LIST ALL w/ Sorting Asc
    public List<Employee> getAllEmployeesSortAsc(String field) {
        return repository.findAll(Sort.by(field).ascending());
    }

    // LIST ALL w/ Sorting Desc
    public List<Employee> getAllEmployeesSortDesc(String field) {
        return repository.findAll(Sort.by(field).descending());
    }

    // LIST ALL w/ Pagination & Sorting Asc
    public Page<Employee> getAllEmployeesPaginationSortAsc(int offset, int size, String field) {
        return repository.findAll(PageRequest.of(offset, size, Sort.by(field)));
    }

    // LIST ALL w/ Pagination & Sorting Desc
    public Page<Employee> getAllEmployeesPaginationSortDesc(int offset, int size, String field) {
        return repository.findAll(PageRequest.of(offset, size, Sort.by(field).descending()));
    }

    public EntityModel<Employee> getEmployeeByName(String lastName, String firstName) {
        if(lastName.isEmpty() || firstName.isEmpty()) {
            throw new IncompleteEmployeeNameException("Request parameter must contain firstname & lastname");
        }
        Optional<Employee> employeeOptionalLastName = repository.findEmployeeByLastName(lastName);
        Optional<Employee> employeeOptionalFirstName = repository.findEmployeeByFirstName(firstName);

            if(!employeeOptionalLastName.isPresent()) {
                throw new EmployeeNotFoundException("Name- " + firstName + " " + lastName + " does not exist");
            }
            if(!employeeOptionalFirstName.isPresent()) {
                throw new EmployeeNotFoundException("Name- " + firstName + " " + lastName + " does not exist");
            }

            Employee employeeTemp1 = employeeOptionalLastName.get();
            Employee employeeTemp2 = employeeOptionalFirstName.get();
            if(employeeTemp1 != employeeTemp2) {
                throw new EmployeeNotFoundException(firstName + " " + lastName + " does not exist"); // or = employeeTemp2 - since they're now referring to the same employee instance.
            }

        // retrieveAllEmployees
        // Simple Implementation of Richardson Maturity Model Level 3
        EntityModel<Employee> resource = EntityModel.of(employeeOptionalFirstName.get());

        // For providing link to all employees.
        WebMvcLinkBuilder link = linkTo(methodOn(EmployeeController.class).retrieveAllEmployees());
        resource.add(link.withRel("all-employees"));

        return resource;
    }

    public ResponseEntity<Object> createEmployee(Employee employee) {
        Employee savedEmployee = repository.save(employee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // returns current URI
                .path("/{id}") // since we don't want to hardcode the current path.
                .buildAndExpand(savedEmployee.getId()).toUri(); // then replace the {id} with saved.Employee.getId() -> one of the method from Employee

        return ResponseEntity.created(location).build();
    }

    public void removeEmployee(Long id) {
        boolean exist = repository.existsById(id);
        if(!exist) {
            throw new EmployeeNotFoundException("id-" + id + " does not exist");
        }

        repository.deleteById(id);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        boolean exist = repository.existsById(id);
        if(!exist) {
            throw new EmployeeNotFoundException("Employee with id " + id + " does not exist");
        }

        Optional<Employee> employeeOptional = repository.findById(id);
        Employee employeeToBeUpdated = employeeOptional.get();
        employeeToBeUpdated.setLastName(employee.getLastName());
        employeeToBeUpdated.setFirstName(employee.getFirstName());
        employeeToBeUpdated.setEmail(employee.getEmail());
        employeeToBeUpdated.setPhone(employee.getPhone());
        employeeToBeUpdated.setHireDate(employee.getHireDate());

        return employeeToBeUpdated;
    }
}
