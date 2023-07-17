package com.vasc.vasc_api.services;

import com.vasc.vasc_api.entities.User;
import com.vasc.vasc_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addNewUser(User user){
        User newUser = this.userRepository.save(user);
        return newUser;
    }


    public Optional<User>
    getUserById(Integer id){return this.userRepository.findById(id);}

    public Iterable<User> getAllUsers(){return this.userRepository.findAll();}

    public User updateUser(Integer id, User userChanges){
        Optional<User> userToUpdateOptional = this.userRepository.findById(id);
        if(!userToUpdateOptional.isPresent()){
            return null;
        }

        User userToUpdate = userToUpdateOptional.get();

        if(userChanges.getFirstName() != null){
            userToUpdate.setFirstName(userChanges.getFirstName());
        }
        if(userChanges.getLastName() != null){
            userToUpdate.setLastName(userChanges.getLastName());
        }
        if(userChanges.getEmail() != null){
            userToUpdate.setEmail(userChanges.getEmail());
        }
        if(userChanges.getPassword() != null){
            userToUpdate.setPassword(userChanges.getPassword());
        }

        User updatedUser = this.userRepository.save(userToUpdate);
        return updatedUser;
    }


    public HashMap<String, Object> deleteUser(Integer id){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            responseMap.put("wasDeleted", false);
            responseMap.put("userInfo", null);
            responseMap.put("Message", "User not found with id: " + id);
            return responseMap;
        }

        responseMap.put("wasDeleted", true);
        responseMap.put("userInfo", optionalUser.get());

        return responseMap;
    }

}
