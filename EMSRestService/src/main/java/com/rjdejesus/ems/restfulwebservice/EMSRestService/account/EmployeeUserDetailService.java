package com.rjdejesus.ems.restfulwebservice.EMSRestService.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(username);

        return user.map(EmployeeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " doesn't exist."));
    }
}

/*
We created this class service where we inject our repository
so that we can interact with our database and load the user.
base on the user input.
*/