package com.park_track.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.park_track.entity.User;
import com.park_track.model.Role;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);


	List<User> findByRole(Role role);

	void deleteByUsername(String username);

}
