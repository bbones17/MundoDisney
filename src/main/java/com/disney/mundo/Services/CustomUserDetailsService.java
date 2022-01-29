package com.disney.mundo.Services;

import com.disney.mundo.Model.Entities.Security.Role;
import com.disney.mundo.Model.Entities.Security.User;
import com.disney.mundo.Model.Repositories.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIdIgnoreCase(Email);
        if(Objects.isNull(user))
                    throw  new UsernameNotFoundException("User not found with email:" + Email);
        else {
            return new org.springframework.security.core.userdetails.User
                    (user.getEmailId(),user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}