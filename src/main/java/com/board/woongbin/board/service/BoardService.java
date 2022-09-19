package com.board.woongbin.board.service;

import com.board.woongbin.board.domain.Board;
import com.board.woongbin.board.repository.BoardRepository;
import com.board.woongbin.board.web.dto.request.BoardRequestDto;
import com.board.woongbin.board.web.dto.response.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // final이나 @NotNill이 붙은 필드의 생성자 생성 자동으로 해줌.
// @Autowired를 쓸 필요 X
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        board.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new BoardResponseDto(board);
    }

    @Transactional
    public Long save(BoardRequestDto request) {
        return boardRepository.save(request.ToEntity()).getId();
    }
}
