package velog.soyeon.security;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    private Users (String password, UserRole userRole) {
        this.password = password;
        this.userRole = userRole;
    }

}
