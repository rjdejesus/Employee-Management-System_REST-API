package com.rjdejesus.ems.restfulwebservice.EMSRestService.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
public class UserController {

    private static final String HAS_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";

    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<User> retrieveAllUserAccounts() {
        return service.getListOfUserAccount();
    }

    @GetMapping("/{username}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public User retrieveUserByUsername(@PathVariable String username) {
        return service.retrieveUser(username);
    }

    @PostMapping("/add")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public String registerNewUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PutMapping("/update-role/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public User updateUserRole(@PathVariable Long id, @RequestParam String role) {
        return service.updateAccountRole(id, role);
    }

    @PutMapping("/update-pass/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public User updateUserPassword(@PathVariable Long id, @RequestParam String password) {
        return service.updateAccountPassword(id, password);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void deleteAccount(@PathVariable Long id) {
        service.removeAccount(id);
    }

}
