package com.facebook_clone.repository;

import com.facebook_clone.model.UsersModel;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersModel, Integer> {
    Optional<UsersModel> findByLoginOrPassword(String login, String password);
    Optional<UsersModel> findByEmail(String email);
}
