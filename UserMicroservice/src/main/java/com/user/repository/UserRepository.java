package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	User findByUsername(String username);

}
