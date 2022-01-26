package velog.soyeon.security.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import velog.soyeon.security.enumeration.UserRole;

@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    private String email;

    @NotNull
    private String passWord;

    @NotNull
    private UserRole userRole;

}
