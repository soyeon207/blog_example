package velog.soyeon.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import velog.soyeon.jwt.dto.UserRequest;
import velog.soyeon.jwt.dto.UserDTO;
import velog.soyeon.jwt.entity.Users;
import velog.soyeon.jwt.repository.UserRepository;
import velog.soyeon.jwt.service.UsersService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserRequest userRequest) {
        Users users = userRepository.save(
                Users.builder().password(bCryptPasswordEncoder.encode(userRequest.getPassWord())).email(userRequest.getEmail()).userRole(userRequest.getUserRole()).build());
        return UserDTO.builder().id(users.getId()).password(users.getPassword()).userRole(users.getUserRole()).email(users.getEmail()).build();
    }

    @Override
    public Users findUser(UserRequest userRequest) {
        return Optional.ofNullable(userRepository.findByEmail(userRequest.getEmail())).orElseThrow(()->new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));
    }


}
