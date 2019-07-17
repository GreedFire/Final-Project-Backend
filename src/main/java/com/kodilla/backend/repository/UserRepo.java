package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    User save(User user);

    Optional<User> findByUsername(String username);


}
