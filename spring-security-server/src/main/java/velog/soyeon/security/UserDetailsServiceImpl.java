package velog.soyeon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsVO loadUserByUsername(String userId) {
        return userRepository.findById(Long.parseLong(userId))
                .map(u -> new UserDetailsVO(UserDTO.builder().id(u.getId()).userRole(u.getUserRole()).password(u.getPassword()).build(), Collections.singleton(new SimpleGrantedAuthority(u.getUserRole().getValue()))))
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
