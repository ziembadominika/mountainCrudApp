package com.crudApp.mountain.security;

import com.crudApp.mountain.domain.Role;
import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MountainAppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MountainAppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found by given username");
        }
        return (UserDetails) new User(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(),
                user.getBirthDate().getDayOfYear(), user.getBirthDate().getMonthValue(), user.getBirthDate().getDayOfMonth(),
                user.getEmail(), user.getDateOfRegistration().getYear(), user.getDateOfRegistration().getMonthValue(),
                user.getDateOfRegistration().getDayOfMonth(), user.getUserRatings(), user.getMountains(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
