package velog.soyeon.jwt.service;

import velog.soyeon.jwt.dto.UserDTO;
import velog.soyeon.jwt.dto.UserRequest;

import java.util.List;

public interface UsersService {
    UserDTO createUser(UserRequest userRequest);

    UserDTO findUser(String email);

    UserDTO findByEmailAndPassword(String email, String password);

    List<UserDTO> findAll();
}
