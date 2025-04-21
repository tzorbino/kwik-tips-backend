package com.zony.kwiktips.service;

import com.zony.kwiktips.DTO.LoginResponseDTO;
import com.zony.kwiktips.model.User;
import com.zony.kwiktips.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zony.kwiktips.security.JwtUtil;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    public LoginResponseDTO login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (BCrypt.checkpw(rawPassword, user.getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());

            LoginResponseDTO response = new LoginResponseDTO();
            response.setMessage("Login successful");
            response.setUserId(user.getId());
            response.setToken(token);
            return response;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

}
