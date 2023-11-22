package com.ssafy.hangil.board.model.service;

import java.util.List;

import com.ssafy.hangil.board.model.BoardDTO;

public interface IBoardService {

	void boardWrite(BoardDTO boardDTO);

	int getBoardNo(BoardDTO boardDTO);
	
	List<BoardDTO> boardList();

	BoardDTO boardDetail(int boardNo);

	void boardDelete(int boardNo);

	List<BoardDTO> userBoardList(String userId);

	List<String> getBoardImgById(int boardNo);

	void setBoardFile(BoardDTO boardDTO, int boardNo);

	void setBoardHashTagContents(BoardDTO boardDTO, int boardNo);

	List<BoardDTO> getBoardList(int page, int limit);
}
