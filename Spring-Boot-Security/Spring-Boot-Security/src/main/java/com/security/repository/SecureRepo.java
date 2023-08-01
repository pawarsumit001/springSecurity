package com.security.repository;

import com.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureRepo extends JpaRepository<User, Integer> {
   public  User findByUsername(String username);
}
