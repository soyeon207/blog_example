package velog.soyeon.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Users, Long> {

//    UserDTO findByUserIdAndPassword(Long id, String password);
//
//    Optional<UserVO> findByUserEmail(String userEmail);

}