package velog.soyeon.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.soyeon.security.UserDTO;
import velog.soyeon.security.dto.UserCreateRequest;
import velog.soyeon.security.service.UsersService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return usersService.createUser(userCreateRequest);
    }


}
