// 1. build.gradle 작성후 2. Application 작성 -> 3. web 패키지 작성&controller 작성

//1.설정: build.gradle, Application.java, JpaConfig.java
//2.도메인: Posts.java (Entity), PostsRepository.java
//3.서비스: PostsService.java
//4.웹(API): PostsApiController.java, HelloController.java
//5.데이터 전달: 각종 Dto 클래스들
//6.테스트

package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장된 WAS 실행하야 SPRING BOOT APPL 구동시킴
                                                        //spring boot 자동설정과 컨테이너 구동
        //1. bean 스캔:springbootapplication 덕분에 @restcontroller, @service, @repository 등의 클래스를 스프링 컨테이너에 등록
        //2. 자동설정 (auto configuration)
        //3. 내장 서버 구동
        //4  준비완료 : 브라우저, 클라이언트 요청을 받을 준비
        //5. hellocontroller.java 작성
        //6. 실행 : localhost:port/hello 입력, 출력확인 http 403 => HelloController의 security permitAll()
    }

}
