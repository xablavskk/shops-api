package com.xvlkk.sp.shops.security.repository;

import com.xvlkk.sp.shops.security.dto.UserDetailsImpl;
import com.xvlkk.sp.shops.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByNmEmail(String nmEmail);
    Optional<User> findUserByNmLogin(String nmLogin);

    default UserDetailsImpl userDetailsDTOToUser(String username){
        return UserDetailsImpl.build(findUserByNmLogin(username).orElseThrow(()-> new UsernameNotFoundException("User not found")));
    }
}
