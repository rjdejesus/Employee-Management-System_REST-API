package com.rjdejesus.ems.restfulwebservice.EMSRestService.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/api/employees")
    public List<Employee> retrieveAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/api/employees/split/{pageNumber}/{pageSize}")
    public Page<Employee> retrieveAllEmployees(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return employeeService.getAllEmployees(pageNumber, pageSize);
    }

    @GetMapping(path = "/api/employees/sort/{field}")
    public List<Employee> retrieveAllEmployeesSortedAsc(@PathVariable String field) {
        return employeeService.getAllEmployeesSortAsc(field);
    }

    @GetMapping(path = "/api/employees/sortdesc/{field}")
    public List<Employee> retrieveAllEmployeesSortedDesc(@PathVariable String field) {
        return employeeService.getAllEmployeesSortDesc(field);
    }
    // Pagination with Sort Asc
    @GetMapping(path = "/api/employees/split/{pageNumber}/{pageSize}/sort/{field}")
    public Page<Employee> retrieveAllEmployeesPaginationSortedAsc(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return employeeService.getAllEmployeesPaginationSortAsc(pageNumber, pageSize, field);
    }

    // Pagination with Sort Desc
    @GetMapping(path = "/api/employees/split/{pageNumber}/{pageSize}/sortdesc/{field}")
    public Page<Employee> retrieveAllEmployeesPaginationSortedDesc(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return employeeService.getAllEmployeesPaginationSortDesc(pageNumber, pageSize, field);
    }

    @GetMapping(path = "/api/employees/find") //localhost:8080/api/employee/?firstname=&lastname= *firstname and lastname required*
    public EntityModel<Employee> retrieveEmployeeByName(
            @RequestParam() String firstname,
            @RequestParam() String lastname) {

        return employeeService.getEmployeeByName(lastname, firstname);
    }

    @PostMapping(path = "/api/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping(path = "/api/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.removeEmployee(id);
    }

    @PutMapping(path = "/api/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
