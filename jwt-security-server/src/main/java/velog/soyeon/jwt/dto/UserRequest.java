package velog.soyeon.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import velog.soyeon.jwt.enumeration.UserRole;

@Getter
@Setter
public class UserRequest {

    private String email;

    private String passWord;

    private UserRole userRole;

}
