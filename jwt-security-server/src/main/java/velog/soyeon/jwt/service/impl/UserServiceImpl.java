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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UserDTO findUser(String email) {
        Users users = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        return UserDTO.builder().id(users.getId()).password(users.getPassword()).userRole(users.getUserRole()).email(users.getEmail()).build();
    }

    @Override
    public UserDTO findByEmailAndPassword(String email, String password) {
        Users users = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));

        if (bCryptPasswordEncoder.matches(password, users.getPassword()) == false) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return UserDTO.builder().id(users.getId()).password(users.getPassword()).userRole(users.getUserRole()).email(users.getEmail()).build();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(u->UserDTO.builder().id(u.getId()).password(u.getPassword()).userRole(u.getUserRole()).email(u.getEmail()).build()).collect(Collectors.toList());
    }


}
