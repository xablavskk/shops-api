package com.xvlkk.sp.shops.security.service;

import com.xvlkk.sp.shops.security.dto.AuthenticationRequestDTO;
import com.xvlkk.sp.shops.security.dto.UserDTO;
import com.xvlkk.sp.shops.security.exceptions.UserNotFound;
import com.xvlkk.sp.shops.security.mapper.ProfileMapper;
import com.xvlkk.sp.shops.security.mapper.UserMapper;
import com.xvlkk.sp.shops.security.model.User;
import com.xvlkk.sp.shops.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtService;

    public String authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Optional<User> user = userRepository.findUserByNmEmail(request.getUsername());

        return user.map(u -> jwtService.generateToken(UserMapper.INSTANCE.mapDetails(u)))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setNmPassword(passwordEncoder.encode(userDTO.getNmPassword()));

        return UserMapper.INSTANCE.map(userRepository.save(UserMapper.INSTANCE.map(userDTO)));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNmUser(userDTO.getNmUser());
                    existingUser.setNmEmail(userDTO.getNmEmail());

                    if (userDTO.getNmPassword() != null && !userDTO.getNmPassword().isEmpty()) {
                        existingUser.setNmPassword(passwordEncoder.encode(userDTO.getNmPassword()));
                    }

                    if (userDTO.getProfiles() != null && !userDTO.getProfiles().isEmpty()) {
                        existingUser.setProfiles(ProfileMapper.INSTANCE.map(userDTO.getProfiles()));
                    }

                    return UserMapper.INSTANCE.map(userRepository.save(existingUser));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO findUserById(Long cdUser){
        return UserMapper.INSTANCE.map(userRepository.findById(cdUser).orElseThrow(() -> new UserNotFound("User not found!")));
    }

    public void deleteUser(Long cdUser) {
        userRepository.deleteById(cdUser);
    }
}
