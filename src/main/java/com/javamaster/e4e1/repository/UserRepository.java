package com.javamaster.e4e1.repository;

import com.javamaster.e4e1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM User u WHERE u.email = :email")
    boolean existsByDetails(@Param("email") String email);

}
