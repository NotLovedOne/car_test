package org.example.car_test.repository;

import org.example.car_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByRefreshToken(String refreshToken);
}
