package com.board.woongbin.board;

import com.board.woongbin.board.domain.Board;
import com.board.woongbin.board.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BoardRepositoryTest {
    String title;
    String content;

    @Autowired BoardRepository boardRepository;

    @Test
    public void save() {
        title = "게시판";
        content = "게시판 만들기";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .writer("강웅빈")
                .build());

        Board result = boardRepository.findById(1L).get();
        assertThat(result.getWriter()).isEqualTo("강웅빈");
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    public void findAll() {
        long boardsCnt = boardRepository.count();
        List<Board> boards = boardRepository.findAll();

        assertThat(boardsCnt).isEqualTo(boards.size());
    }

    @Test
    public void delete() {
        Board entity = boardRepository.findById(1L).get();
        boardRepository.delete(entity);
    }
}