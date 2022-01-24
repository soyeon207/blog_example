package velog.soyeon.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class UserDetailsVO implements UserDetails {

    private final UserDTO userDTO;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return userVO.getIsEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return userVO.getIsEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return userVO.getIsEnable();
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return userVO.getIsEnable();
    }
}
