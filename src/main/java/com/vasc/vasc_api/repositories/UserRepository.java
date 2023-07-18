package com.vasc.vasc_api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.vasc.vasc_api.entities.User;
public interface UserRepository extends CrudRepository<User, Integer> {


}
