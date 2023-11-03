package com.ssafy.hangil.board.model.service;

import java.util.List;

import com.ssafy.hangil.board.model.BoardDTO;

public interface IBoardService {

	void boardWrite(BoardDTO boardDTO);

	List<BoardDTO> boardList();

	BoardDTO boardDetail(int boardNo);

	void boardDelete(int boardNo);

	List<BoardDTO> userBoardList(String userId);

}
