package app.web;

import app.model.UserEntity;
import app.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository myUserRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity myUser = myUserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        //Перенесли данные из нашего класса UserEntity в UserDetails (реализованный на базе класса User)
        UserDetails user = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRole().toString())
                .build();
        return user;
    }
}