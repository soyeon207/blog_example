package velog.soyeon.security;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String password;

    private UserRole userRole;

    @Builder
    private UserDTO(Long id, String password, UserRole userRole) {
        this.id = id;
        this.password = password;
        this.userRole = userRole;
    }

}
