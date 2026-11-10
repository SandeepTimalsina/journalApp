package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/id/{myId}")
    public User getById(@PathVariable ObjectId myId) {
        return userService.getById(myId).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/id/{myId}")
    public User updateUser(@PathVariable ObjectId myId,
                           @RequestBody User user) {

        User old = userService.getById(myId).orElse(null);

        if (old != null) {

            old.setUserName(user.getUserName() != null &&
                    !user.getUserName().isEmpty()
                    ? user.getUserName()
                    : old.getUserName());

            old.setPassword(user.getPassword() != null &&
                    !user.getPassword().isEmpty()
                    ? user.getPassword()
                    : old.getPassword());

            userService.saveUser(old);
        }

        return old;
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteUser(@PathVariable ObjectId myId) {
        userService.deleteById(myId);
        return true;
    }
}