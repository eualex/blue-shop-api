package com.blue.api.repository;

import com.blue.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDetailsRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
