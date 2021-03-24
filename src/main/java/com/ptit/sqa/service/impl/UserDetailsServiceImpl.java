package com.ptit.sqa.service.impl;

import com.ptit.sqa.entity.CustomUserDetails;
import com.ptit.sqa.entity.User;
import com.ptit.sqa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser= userRepository.findByUsername(s);
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(optionalUser.get());
    }

    public UserDetails loadUserById(long id) {
        Optional<User> optionalUser= userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(optionalUser.get());
    }
}
