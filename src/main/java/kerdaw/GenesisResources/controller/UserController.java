package kerdaw.GenesisResources.controller;

import kerdaw.GenesisResources.model.User;
import kerdaw.GenesisResources.service.UserException;
import kerdaw.GenesisResources.dto.UserDTO;
import kerdaw.GenesisResources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserDTO newUser){
        User addedUser;
        try {
            addedUser = userService.addUser(newUser);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return ResponseEntity.ok(addedUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id, @RequestParam(required = false) boolean detail){
        User foundUser;
        try {
            foundUser = userService.getUserById(detail, id);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) boolean detail){
        List<User> users = userService.getUsers(detail);
        if (users == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(users);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser){
        User users;
        try {
            users = userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id){
        boolean removed;
        try {
            removed = userService.deleteUser(id);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        return ResponseEntity.ok(removed);
    }
}
