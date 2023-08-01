package com.security.services;

import com.security.models.User;
import com.security.repository.SecureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServices {

    @Autowired
    private SecureRepo secureRepo;

    public List<User> getAllUsers() {
        return this.secureRepo.findAll();
    }


    public User addUser(User user) {

        return this.secureRepo.save(user);
    }

    public Optional<User> getUser(int id) {
        return this.secureRepo.findById(id);
    }


    public void deleteUser(int id) {
        secureRepo.deleteById(id);
    }

    public User updateUser(User user) {
        return this.updateUser(user);
    }
}
