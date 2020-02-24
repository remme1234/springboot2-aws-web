package com.remme.springboot.service.posts;

import com.remme.springboot.domain.posts.Posts;
import com.remme.springboot.domain.posts.PostsRepository;
import com.remme.springboot.web.dto.PostsListResponseDto;
import com.remme.springboot.web.dto.PostsResponseDto;
import com.remme.springboot.web.dto.PostsSaveRequestDto;
import com.remme.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 해당 어노테이션이 대신 생성해 준 것
// 생성자를 직접 안 쓰고 롬복 어노테이션을 사용한 이유는 해당 클래스의 의존선 관계가 변경될 때마다 생성자 코드를 꼐속해서
// 수정하는 번거로움을 해결하기 위함
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);

    }

    // readOnly 옵션이 추가되면 트랜잭션 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선되기 때문에
    // 등록, 수정, 삭제 기능이 전혀 업는 서비스 메서드에서 사용한다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                            // == .map(posts .> new PostsListResponseDto(posts)) 와 동일한 코드,
                            // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> list로 반환하는 메소드
                            .map(PostsListResponseDto::new)
                            .collect(Collectors.toList());

    }

    // jpaRepository에서 이미 delete 메서드를 지원하고 있으니 활용한다.
    // 엔티티를 파라미터로 삭제할 수 있고, deleteById메서드를 이용하면 id로 삭제할 수도 있다.
    // 존제하는 Posts인지 확인을 위해 엔티티조회 후 그대로 삭제한다.
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
