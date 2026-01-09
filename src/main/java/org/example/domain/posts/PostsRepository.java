// 인터페이스로 스프링 데이터 JPA ㅎ사용으로 적은 코드로 복잡한 DB작업 처리
//다음 단계로 비즈니스 로직을 처리하는 서브스 클래스를 만듬
package org.example.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //jpa query language로 Posts라는 클래스를 p라는 객체이름으로 사용
    List<Posts> findAllDesc();


}