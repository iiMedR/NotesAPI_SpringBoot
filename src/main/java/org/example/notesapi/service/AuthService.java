package org.example.notesapi.service;

import jakarta.transaction.Transactional;
import org.example.notesapi.dto.RegisterRequest;
import org.example.notesapi.dto.UserResponse;
import org.example.notesapi.exception.EmailAlreadyExistsException;
import org.example.notesapi.model.User;
import org.example.notesapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public UserResponse RegisterUser(RegisterRequest request){
        User user = new User();

        if(userRepository.existsByEmail(request.email())){
            throw  new EmailAlreadyExistsException(request.email());
        }
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setUsername(request.username());
        user.setEmail(request.email());

        userRepository.save(user);
        return toResponse(user);
    }

}
