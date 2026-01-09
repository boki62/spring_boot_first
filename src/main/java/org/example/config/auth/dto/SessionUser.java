package org.example.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor; // 추가
import org.example.domain.member.Member;

import java.io.Serializable;

@Getter
@NoArgsConstructor // 1. 기본 생성자 추가 (중요)
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Member user) {
        if (user != null) { // 2. Null 방어 코드 추가
            this.name = user.getName();
            this.email = user.getEmail();
            this.picture = user.getPicture();
        }
    }
}