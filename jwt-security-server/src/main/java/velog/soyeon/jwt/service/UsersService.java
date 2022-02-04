package velog.soyeon.jwt.service;

import velog.soyeon.jwt.dto.UserRequest;
import velog.soyeon.jwt.dto.UserDTO;
import velog.soyeon.jwt.entity.Users;

public interface UsersService {
    UserDTO createUser(UserRequest userRequest);

    Users findUser(UserRequest userRequest);
}
