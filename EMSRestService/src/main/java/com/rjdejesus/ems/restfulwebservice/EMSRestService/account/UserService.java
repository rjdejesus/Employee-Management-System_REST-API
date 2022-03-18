package com.rjdejesus.ems.restfulwebservice.EMSRestService.account;

import com.rjdejesus.ems.restfulwebservice.EMSRestService.exception.AccountNotFoundException;
import com.rjdejesus.ems.restfulwebservice.EMSRestService.exception.IncompleteFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getListOfUserAccount() {
        return repository.findAll();
    }

    public User retrieveUser(String username) {
        Optional<User> userToFind = repository.findByUserName(username);

        if(!userToFind.isPresent())
            throw new AccountNotFoundException("Account username-" + username + " doesn't exist");

        return userToFind.get();
    }

    public String registerUser(User user) {
        if(user.getUserName().isEmpty() || user.getPassword().isEmpty())
            throw new IncompleteFieldException("Account must have username and password.");
        if(user.getRole() == null)
            user.setRole(DEFAULT_ROLE);

        String encryptedPWD = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPWD);
        repository.save(user);

        return "Account successfully registered";
    }

    @Transactional
    public User updateAccountRole(Long id, String role) {
        boolean accountExist = repository.existsById(id);
        if(!accountExist)
            throw new AccountNotFoundException("Account id-" + id + " doesn't exist.");

        Optional<User> userOptional = repository.findById(id);
        User userToBeUpdated = userOptional.get();
        userToBeUpdated.setRole(role);

        return userToBeUpdated;
    }

    @Transactional
    public User updateAccountPassword(Long id, String newPassword) {
        boolean accountExist = repository.existsById(id);
        if(!accountExist)
            throw new AccountNotFoundException("Account id-" + id + " doesn't exist.");

        Optional<User> userOptional = repository.findById(id);
        User user = userOptional.get();
        String encryptedPWD = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPWD);

        return user;
    }

    public void removeAccount(Long id) {
        boolean accountExist = repository.existsById(id);
        if(!accountExist)
            throw new AccountNotFoundException("Account id-" + id + " doesn't exist.");

        repository.deleteById(id);
    }
}
