package com.zony.kwiktips.controller;

import com.zony.kwiktips.DTO.LoginRequestDTO;
import com.zony.kwiktips.DTO.LoginResponseDTO;
import com.zony.kwiktips.DTO.UserDTO;
import com.zony.kwiktips.model.User;
import com.zony.kwiktips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        UserDTO dto = new UserDTO();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        dto.setEmail(savedUser.getEmail());

        savedUser.setPassword(null);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

}
