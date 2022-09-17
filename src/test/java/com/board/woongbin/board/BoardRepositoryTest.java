package com.board.woongbin.board;

import com.board.woongbin.board.domain.Board;
import com.board.woongbin.board.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardRepositoryTest {
    String title;
    String content;

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    public void clean() {
        boardRepository.deleteAll();
    }

    @Test
    public void save() {
        title = "게시판";
        content = "게시판 만들기";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .writer("강웅빈")
                .build());
    }

    @Test
    public void findAll() {
        List<Board> list = boardRepository.findAll();
        Board board = list.get(0);

        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
    }
}