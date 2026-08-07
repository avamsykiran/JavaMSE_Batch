package com.cts.restapidemo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.restapidemo.entities.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findByUsername(String username);
	boolean existsByUsername(String username);
}