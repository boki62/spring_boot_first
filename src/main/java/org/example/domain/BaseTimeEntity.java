//실시간 자동으로 시간을 기록
package org.example.domain; // <-- 'posts'가 아닌 상위 'domain' 패키지에 위치

import lombok.Getter;
import jakarta.persistence.EntityListeners; // Spring Boot 3.x 환경에서는 jakarta 사용
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter // 1. Getter 메서드 자동 생성 (테스트 오류 해결)
@MappedSuperclass // 2. 상속받는 엔티티의 컬럼으로 포함
@EntityListeners(AuditingEntityListener.class) // 3. Auditing 기능 포함
public abstract class BaseTimeEntity {

    @CreatedDate // 4. 엔티티 생성 시 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 5. 엔티티 수정 시 시간 자동 저장
    private LocalDateTime modifiedDate;
}