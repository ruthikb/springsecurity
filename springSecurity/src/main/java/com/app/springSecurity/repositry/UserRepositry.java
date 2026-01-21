package com.app.springSecurity.repositry;

import com.app.springSecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<UserEntity, Integer> {
}
