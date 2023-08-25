package com.faizal.HybridDatabase.controller;

import com.faizal.HybridDatabase.model.User;
import com.faizal.HybridDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Optional<User> optuser = userService.findById(id);
        User user = optuser.get();
        if (user == null) {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }
    @PostMapping(value = "/create", headers = "Accept=application/json")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void> (headers, HttpStatus.CREATED);
    }
    @GetMapping(value = "/get", headers = "Accept=application/json")
    public List<User> getAllUser() {
        List<User> tasks = userService.getUser();
        return tasks;
    }
    @PutMapping(value = "/update", headers = "Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody User currentUser) {
        System.out.println("sd");
        Optional<User> optuser = userService.findById(currentUser.getId());
        User user = optuser.get();
        if (user == null) {
            return new ResponseEntity<String> (HttpStatus.NOT_FOUND);
        }
        userService.update(currentUser, currentUser.getId());
        return new ResponseEntity<String> (HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        Optional<User> optuser = userService.findById(id);
        User user = optuser.get();
        if (user == null) {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User> (HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser) {
        Optional<User> optuser = userService.findById(id);
        User user = optuser.get();
        if (user == null) {
            return new ResponseEntity<User> (HttpStatus.NOT_FOUND);
        }
        User usr = userService.updatePartially(currentUser, id);
        return new ResponseEntity<User> (usr, HttpStatus.OK);
    }
}
