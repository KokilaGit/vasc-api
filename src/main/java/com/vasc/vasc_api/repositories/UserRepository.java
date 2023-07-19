package com.vasc.vasc_api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.vasc.vasc_api.entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> getUserByLastName(String lastName);
    List<User> getUserByEmail(String email);
}
