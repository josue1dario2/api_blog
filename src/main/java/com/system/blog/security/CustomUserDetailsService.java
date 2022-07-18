package com.system.blog.security;

import com.system.blog.entities.Role;
import com.system.blog.entities.User;
import com.system.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final  UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with that username or email : "+usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapperRoles(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapperRoles(Set<Role> roles){
        return roles.stream().map(role ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
