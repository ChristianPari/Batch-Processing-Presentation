package com.christianpari.springbatchrunthrough1.repository;

import com.christianpari.springbatchrunthrough1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
