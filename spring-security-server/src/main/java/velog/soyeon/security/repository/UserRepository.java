package velog.soyeon.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import velog.soyeon.security.entity.Users;

@Repository
public interface UserRepository extends JpaRepository <Users, Long> {
    Users findByEmail(String email);
}