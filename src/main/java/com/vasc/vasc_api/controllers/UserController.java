package com.vasc.vasc_api.controllers;


import com.vasc.vasc_api.entities.User;
import com.vasc.vasc_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService){ this.userService = userService; }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        User savedUser = userService.addNewUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>>  getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User userChanges){
        return ResponseEntity.ok(userService.updateUser(id, userChanges));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteUser(@PathVariable("id") Integer id) {


        HashMap<String, Object> responseMap = userService.deleteUser(id);

        if (responseMap.get("userInfo") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }

        return ResponseEntity.ok(responseMap);

    }

    @GetMapping("/name")
    public List<User> getUserByLastName(@RequestParam String lastName){
        return userService.getUserByLastName(lastName);
    }

    @GetMapping("/email")
    public List<User> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

}
