package com.board.woongbin.board.web.api;

import com.board.woongbin.board.service.BoardService;
import com.board.woongbin.board.web.dto.request.BoardRequestDto;
import com.board.woongbin.board.web.dto.response.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/create/board")
    public Long save(@RequestBody BoardRequestDto requestDto) {
        return boardService.save(requestDto);
    }


    @PutMapping("/update/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @PutMapping("/select/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }
}
