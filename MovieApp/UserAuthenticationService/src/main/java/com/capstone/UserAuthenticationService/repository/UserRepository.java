package com.capstone.UserAuthenticationService.repository;

import com.capstone.UserAuthenticationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

 User findByEmailAndPassword(String emailId, String password);
}
