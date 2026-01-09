//외부와 통신하는 광ㄴ문인 api 컨트롤러(json형식), PostsService.java를 호출
//흐름 : Request(클라이언트가 특정주소로 데이터 보냄) ->
//      Controller(@PostMapping이 요청을 받고, 데이터를 PostsSaveRequestDto에 담아 Service로 넘김)
//      Service: 비즈니스 로직(트랜잭션 처리 등)을 수행하고 Repository를 통해 DB에 저장.
//      Response: 저장된 게시글의 id를 다시 Controller를 거쳐 클라이언트에게 응답.
//테스트 : *Test를 만들어 mockMvc 테스트, Postman/iNSOMNIA 로 HTTP 요청, 화면 연결(mUSTACHE/tHYMELEAF)

package org.example.web;

import org.example.service.posts.PostsService;
import org.example.web.dto.PostsListResponseDto;
import org.example.web.dto.PostsResponseDto;
import org.example.web.dto.PostsSaveRequestDto;
import org.example.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor //서비스 레이어와 마찬가지로 final이 붙은 postsService를 생성자로 주입받기 위해 사용
@RestController  // 이 컨트롤러는 JSON을 반환하는 컨트롤러임을 명시
public class PostsApiController {

    private final PostsService postsService;

    //@RequestBody: 클라이언트가 보낸 JSON 데이터를 자바 객체(DTO)로 변환, v1의미:버전관리
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //@PathVariable: URL 경로에 포함된 값({id})을 메소드의 파라미터로 가져옴
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
