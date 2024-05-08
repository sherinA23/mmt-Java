package com.poc.makemytrip.dao;

import com.poc.makemytrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDao extends JpaRepository<User, Integer> {
    Optional<User> findByEmailID(String email);
}
