package com.antspace.GraphNest.service;

import com.antspace.GraphNest.models.User;
import com.antspace.GraphNest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(String search) {
        if (search == null || search.isBlank()) return userRepository.findAll();
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search);
    }

    public User addUser(String name, String email, String role) {
        User u = new User(null, name, email, role);
        return userRepository.save(u);
    }

    public User updateUser(Long id, String name, String email, String role) {
        return userRepository.findById(id).map(user -> {
            if (name != null) user.setName(name);
            if (email != null) user.setEmail(email);
            if (role != null) user.setRole(role);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
