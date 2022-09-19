package com.board.woongbin.board;

import com.board.woongbin.board.domain.Board;
import com.board.woongbin.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;

    @Test
    public void save() {
        String title = "강웅빈 바보";
        String content = "glgl";
        String writer = "이창보";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build());

        Board findedBoard = boardRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("나가 죽으십시오"));

        assertThat(findedBoard.getTitle()).isEqualTo(title);
        assertThat(findedBoard.getContent()).isEqualTo(content);
        assertThat(findedBoard.getWriter()).isEqualTo(writer);
    }

    @Test
    public void findAll() {
        long boardsCnt = boardRepository.count();
        List<Board> boards = boardRepository.findAll();

        assertThat(boardsCnt).isEqualTo(boards.size());
    }

    @Test
    public void delete() {
        String title = "강웅빈 바보";
        String content = "glgl";
        String writer = "이창보";

        Board save = boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build());

        boardRepository.delete(save);
    }
}