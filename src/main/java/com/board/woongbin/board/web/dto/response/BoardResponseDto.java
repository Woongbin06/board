package com.board.woongbin.board.web.dto.response;

import com.board.woongbin.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
    }
}
