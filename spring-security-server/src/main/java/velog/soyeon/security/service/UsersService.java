package velog.soyeon.security.service;

import velog.soyeon.security.dto.UserDTO;
import velog.soyeon.security.dto.UserCreateRequest;

public interface UsersService {

    UserDTO createUser(UserCreateRequest userCreateRequest);
}
