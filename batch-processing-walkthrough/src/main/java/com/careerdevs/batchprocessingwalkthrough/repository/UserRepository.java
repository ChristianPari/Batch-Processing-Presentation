package com.careerdevs.batchprocessingwalkthrough.repository;

import com.careerdevs.batchprocessingwalkthrough.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
