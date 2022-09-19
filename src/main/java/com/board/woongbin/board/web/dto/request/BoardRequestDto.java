package com.board.woongbin.board.web.dto.request;

import com.board.woongbin.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BoardRequestDto {

    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardRequestDto(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board ToEntity(){
        return Board.
                builder()
                .title(title)
                .writer(writer)
                .content(content)
                .build();
    }
}