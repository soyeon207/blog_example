package velog.soyeon.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import velog.soyeon.security.UserNotFoundException;
import velog.soyeon.security.UserRepository;
import velog.soyeon.security.Users;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Users loadUserByUsername(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

}