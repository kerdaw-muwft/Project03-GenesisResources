package kerdaw.GenesisResources.Controller;

import kerdaw.GenesisResources.Model.User;
import kerdaw.GenesisResources.dto.UserDTO;
import kerdaw.GenesisResources.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserDTO newUser){

        User addedUser = userService.addUser(newUser);
        if (addedUser == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(addedUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id, @RequestParam(required = false) boolean detail){
        User addedUser = userService.getUserById(detail, id);
        if (addedUser == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(addedUser);
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
        User users = userService.updateUser(updatedUser);
        if (users == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id){
        boolean removed = userService.deleteUser(id);
        if (!removed){
            return ResponseEntity.internalServerError().body(removed);
        }
        return ResponseEntity.ok(removed);
    }
}
