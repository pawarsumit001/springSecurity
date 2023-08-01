package com.security.controllers;

import com.security.models.User;
import com.security.services.UserCrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserCrudServices userCrudServices;

    @GetMapping("/")
    public List<User> getAllUser() {
        return this.userCrudServices.getAllUsers();
    }

/* this is used to get access
    @PreAuthorize("hasRole('ADMIN')")
*
* */
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id) {
        return this.userCrudServices.getUser(id);
    }

    @PostMapping("/")
    public User add(@RequestBody User user) {
        return this.userCrudServices.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.userCrudServices.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User UpdateUser(@PathVariable("id") int id,User user) {

        return   this.userCrudServices.updateUser(user);
    }


}
