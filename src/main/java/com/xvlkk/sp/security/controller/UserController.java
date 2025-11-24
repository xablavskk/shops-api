package com.xvlkk.sp.security.controller;

import com.xvlkk.sp.security.dto.AuthenticationRequestDTO;
import com.xvlkk.sp.security.dto.UserDTO;
import com.xvlkk.sp.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user/v1/authenticate/")
public class UserController {
    private final UserService userService;

    @GetMapping("{cdUser}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long cdUser){
        return ResponseEntity.ok(userService.findUserById(cdUser));
    }

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestDTO request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{cdUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long cdUser) {
        userService.deleteUser(cdUser);
        return ResponseEntity.noContent().build();
    }
}
