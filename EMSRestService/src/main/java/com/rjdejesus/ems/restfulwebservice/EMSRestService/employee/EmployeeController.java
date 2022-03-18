package com.rjdejesus.ems.restfulwebservice.EMSRestService.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final String HAS_ROLE_ADMIN_HR = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_HR')";
    private static final String HAS_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public List<Employee> retrieveAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/split/{pageNumber}/{pageSize}")
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public Page<Employee> retrieveAllEmployees(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return employeeService.getAllEmployees(pageNumber, pageSize);
    }

    @GetMapping("/sort/{field}")
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public List<Employee> retrieveAllEmployeesSortedAsc(@PathVariable String field) {
        return employeeService.getAllEmployeesSortAsc(field);
    }

    @GetMapping("/sortdesc/{field}")
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public List<Employee> retrieveAllEmployeesSortedDesc(@PathVariable String field) {
        return employeeService.getAllEmployeesSortDesc(field);
    }
    // Pagination with Sort Asc
    @GetMapping("/split/{pageNumber}/{pageSize}/sort/{field}")
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public Page<Employee> retrieveAllEmployeesPaginationSortedAsc(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return employeeService.getAllEmployeesPaginationSortAsc(pageNumber, pageSize, field);
    }

    // Pagination with Sort Desc
    @GetMapping("/split/{pageNumber}/{pageSize}/sortdesc/{field}")
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public Page<Employee> retrieveAllEmployeesPaginationSortedDesc(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return employeeService.getAllEmployeesPaginationSortDesc(pageNumber, pageSize, field);
    }

    @GetMapping("/find") //localhost:8080/api/employee/?firstname=&lastname= *firstname and lastname required*
    @PreAuthorize(HAS_ROLE_ADMIN_HR)
    public EntityModel<Employee> retrieveEmployeeByName(
            @RequestParam() String firstname,
            @RequestParam() String lastname) {

        return employeeService.getEmployeeByName(lastname, firstname);
    }

    // ADMIN ONLY
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Object> addNewEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // ADMIN ONLY
    @DeleteMapping("/remove/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.removeEmployee(id);
    }

    // ADMIN ONLY
    @PutMapping("/update/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
