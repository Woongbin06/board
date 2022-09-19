package com.board.woongbin.board;

import com.board.woongbin.board.domain.Board;
import com.board.woongbin.board.repository.BoardRepository;
import com.board.woongbin.board.web.dto.request.BoardRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    private BoardRequestDto boardRequestDto;

    @AfterEach
    public void tearDown() throws Exception {
        boardRepository.deleteAll();
    }

    @Test
    public void 게시글생성() throws Exception{
        String title = "게시글";
        String content = "게시글 생성";
        String writer = "강웅빈";

        BoardRequestDto requestDto = BoardRequestDto.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        String url = "http://localhost:" + port + "/create/board";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
    
    @Test
    public void 게시글수정() throws Exception {
        Board saveBoard = boardRepository.save(Board.builder()
                .title("제목")
                .content("안생겨요")
                .writer("강웅빈")
                .build());

        Long updateId = saveBoard.getId();
        String changeTitle = "제목2";
        String changeContent = "안생겨요2";

        BoardRequestDto requestDto = BoardRequestDto.builder()
                .title(changeTitle)
                .content(changeContent)
                .build();

        String url = "http://localhost:" + port + "/update/board/" + updateId;
        HttpEntity<BoardRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(changeTitle);
        assertThat(all.get(0).getContent()).isEqualTo(changeContent);
    }
}
