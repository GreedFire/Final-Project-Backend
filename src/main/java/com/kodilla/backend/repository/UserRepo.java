package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(nativeQuery = true)
    void signIn(@Param("ID") long id);

    @Modifying
    @Query(nativeQuery = true)
    void signOut(@Param("ID") long id);

    @Modifying
    @Query(nativeQuery = true)
    void updatePassword(@Param("ID") long id, @Param("NEWPASSWORD") String newPassword);

    @Modifying
    @Query(nativeQuery = true)
    void deleteUser(@Param("ID") long id, @Param("PASSWORD") String newPassword);

    @Query(nativeQuery = true)
    Optional<User> checkOldPassword(@Param("ID") long id, @Param("OLDPASSWORD") String oldPassword);






}
