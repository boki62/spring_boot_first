package org.example.web;

import org.example.web.SecurityConfig; // 본인의 패키지 경로에 맞게 수정
import org.junit.jupiter.api.Test; // JUnit 5 사용
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = RANDOM_PORT)
//@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    //private MockMvc mvc;
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        // mvc.perform 대신 restTemplate.getForObject 사용
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        System.out.println(body); //에러가 난다면 여기에 에러 페이지 html이 찍힐 것
        // AssertJ를 사용한 검증
        assertThat(body).isNotNull();
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }
}
