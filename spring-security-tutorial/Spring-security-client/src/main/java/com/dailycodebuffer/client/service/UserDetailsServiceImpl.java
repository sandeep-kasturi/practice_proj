package com.dailycodebuffer.client.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.client.entity.User;
import com.dailycodebuffer.client.model.MyUserDetails;
import com.dailycodebuffer.client.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // //UserDetailService unimplemented method (scenario for each user have only one role)
    // @Override
    // public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        
    //     User user = userRepository.findByFirstName(firstName);
         
    //     if (user == null) {
    //         throw new UsernameNotFoundException("Could not find user");
    //     }
         
    //     return new MyUserDetails(user);
    // }





    // //UserDetailService unimplemented method (scenario for each user can have many roles)
    // @Override
    // public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        
    //     Optional<User> user = userRepository.findByFirstName(firstName);
        
    //     user.orElseThrow(() -> new UsernameNotFoundException(firstName + " not found."));

    //     return user.map(MyUserDetails::new).get();
    // }

 
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        
        User user = userRepository.findByFirstName(firstName);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
}