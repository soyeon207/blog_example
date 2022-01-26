package velog.soyeon.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import velog.soyeon.security.repository.UserRepository;
import velog.soyeon.security.entity.Users;
import velog.soyeon.security.dto.UserCreateRequest;
import velog.soyeon.security.dto.UserDTO;
import velog.soyeon.security.service.UsersService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserCreateRequest userCreateRequest) {
        Users users = userRepository.save(
                Users.builder().password(bCryptPasswordEncoder.encode(userCreateRequest.getPassWord())).email(userCreateRequest.getEmail()).userRole(userCreateRequest.getUserRole()).build());
        return UserDTO.builder().id(users.getId()).password(users.getPassword()).userRole(users.getUserRole()).email(users.getEmail()).build();
    }

}
