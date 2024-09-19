package com.spring.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.cache.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
