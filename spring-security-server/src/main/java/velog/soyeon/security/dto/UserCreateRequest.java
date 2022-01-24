package velog.soyeon.security.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import velog.soyeon.security.UserRole;

@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    private String passWord;

    @NotNull
    private UserRole userRole;

}
