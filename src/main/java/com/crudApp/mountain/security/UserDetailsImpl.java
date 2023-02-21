package com.crudApp.mountain.security;

import com.crudApp.mountain.domain.Mountain;
import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserRating;
import com.crudApp.mountain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private LocalDate dateOfRegistration;
    private List<UserRating> userRatings;
    private List<Mountain> mountains;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String userName, String firstName, String lastName, LocalDate birthDate, String email,
                           LocalDate dateOfRegistration, List<UserRating> userRatings, List<Mountain> mountains,
                           String password, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.dateOfRegistration = dateOfRegistration;
        this.userRatings = userRatings;
        this.mountains = mountains;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthDate(),
                userEntity.getEmail(),
                userEntity.getDateOfRegistration(),
                userEntity.getUserRatings(),
                userEntity.getMountains(),
                userEntity.getPassword(),
                authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found by given username");
        }
        return (UserDetails) UserDetailsImpl.build(userEntity);
    }
}
