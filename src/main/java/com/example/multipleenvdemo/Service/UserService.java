package com.example.multipleenvdemo.Service;

import com.example.multipleenvdemo.entity.User;
import com.example.multipleenvdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User createUser( User user) {
        return repo.save(user);
    }

    public User updateUser(Long id, User updated) {
        return repo.findById(id).map(user -> {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
            return repo.save(user);
        }).orElseThrow();
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
