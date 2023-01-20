package com.ile.app.controller;

import com.ile.app.exceptions.UserNotFoundException;
import com.ile.app.model.User;
import com.ile.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//this is required so that frontend and backend can communicate
@CrossOrigin("http://localhost:3000/")
public class UserController {
    private final UserService _userService;

    public UserController(UserService _userService) {
        this._userService = _userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            var users = _userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id){
        try{
            var user = _userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        try{
            _userService.saveUser(user);
            return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("User was not able to be saved", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable(name = "id") Long Id){
        try{
            _userService.updateUser(user,Id);
            return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("User was not able to be updated", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id){
        try{
            _userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("User was not found or wasn't able to be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
