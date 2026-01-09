//1. build.gradle 2. Application, 3. web 패키지 생성후 HelloController
//기초적인 관문 역할
// 클라이언트(브라우저나 앱)의 HTTP 요청을 가장 먼저 맞이하는 컨트롤러 클래스
// 테스트
// 1.URL 일치: @GetMapping("/hello") 경로와 테스트의 get("/hello")가 같아야 함.
// 2. 응답값 일치: return "hello"; 문자열과 테스트의 .andExpect(content().string("hello"))가 완벽히 같아야 함.
// (대소문자, 공백 주의!)
package org.example.web;

import org.example.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // 중요!

// @RestController는 @Controller + @ResponseBody의 역할을 합니다.
// 즉, 메서드가 반환하는 문자열을 HTTP 응답 본문에 직접 작성하도록 지시합니다.
@RestController //json을 반환하는 컨트롤러로 지정
public class HelloController {

    // "/hello" 경로로 GET 요청이 오면 이 메서드를 실행합니다.
    @GetMapping("/hello")
    public String hello() {
        // 정확히 "hello"라는 문자열을 반환해야 테스트가 성공합니다.
        return "hello"; //return 값이 다르면 오류 발생 HelloControllerTest에서...
    }
    //어노테이션 - 코드에 추가적인 정보를 제공하는 메타데이터의한 형태
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, //외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
