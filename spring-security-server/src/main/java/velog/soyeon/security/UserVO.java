package velog.soyeon.security;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "USER")
@Getter
public class UserVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;                                        // 고유번호

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;                        // 등록 일자

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime updatedAt;                        // 수정 일자

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnable = true;                        // 사용 여부

    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public UserVO(String userEmail, String userPw){
        this.userEmail = userEmail;
        this.userPw = userPw;
    }
}
