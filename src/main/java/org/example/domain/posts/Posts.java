// 엔티티 클래스. 테이블과 1:1 매핑
package org.example.domain.posts;

import lombok.AllArgsConstructor;
import org.example.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity; // @Entity
import jakarta.persistence.Id; // @Id
import jakarta.persistence.GeneratedValue; // @GeneratedValue
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column; // @Column

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity  //테이블과 링크될 클래스임을 나타냄
//public class Posts extends BaseTimeEntity {
public class Posts extends BaseTimeEntity {  //상속으로 부모에 있는 메서드로 날짜가 자동 기록

    @Id  //해당 테이블의 pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk의 생성규칙을 나타냄
    private Long id;

    @Column(length = 500, nullable = false)  //모든 필드, 컬럼이 됨
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    //어느 필드에 어떤 값을 채워야 할지 명확하게 해줌,
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
