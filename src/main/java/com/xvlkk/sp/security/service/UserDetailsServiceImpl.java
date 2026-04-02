package com.xvlkk.sp.security.service;

import com.xvlkk.sp.security.mapper.UserMapper;
import com.xvlkk.sp.security.model.User;
import com.xvlkk.sp.security.dto.UserDetailsImpl;
import com.xvlkk.sp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByNmEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return UserMapper.INSTANCE.mapDetails(user);
    }
}
