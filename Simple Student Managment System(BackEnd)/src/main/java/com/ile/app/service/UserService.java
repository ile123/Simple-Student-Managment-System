package com.ile.app.service;

import com.ile.app.exceptions.UserNotFoundException;
import com.ile.app.model.User;
import com.ile.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }
    public List<User> getAllUsers() { return _userRepository.findAll(); }
    public User getUser(Long id){ return _userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)); }
    public void deleteUser(Long id){
        if(!_userRepository.existsById(id)){
            return;
        }
        _userRepository.deleteById(id);
    }
    public void saveUser(User user){
        _userRepository.save(user);
    }
    public void updateUser(User user, Long id) {
        if(user == null){
            return;
        }
        var temp = getUser(id);
        if(!user.getUsername().isEmpty() && !user.getUsername().isBlank()){
            temp.setUsername(user.getUsername());
        }
        if(!user.getName().isEmpty() && !user.getName().isBlank()){
            temp.setName(user.getName());
        }
        if(!user.getEmail().isEmpty() && !user.getEmail().isBlank()){
            temp.setEmail(user.getEmail());
        }
        _userRepository.save(temp);
    }

}
