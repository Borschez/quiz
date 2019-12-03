package ru.borsch.quizserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.borsch.quizserver.model.User;
import ru.borsch.quizserver.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceEx implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", userName));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);

        return userDetails;
    }
}
