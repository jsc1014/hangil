package com.ssafy.hangil.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.board.model.mapper.IBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements IBoardService {

	private final IBoardMapper boardMapper;

	@Override
	public void boardWrite(BoardDTO boardDTO) {
		boardMapper.boardWrite(boardDTO);
	}

	@Override
	public int getBoardNo(BoardDTO boardDTO) {
		return boardMapper.getBoardNo(boardDTO);
	}

	@Override
	public void setBoardFile(BoardDTO boardDTO, int boardNo) {
		List<String> fileCids = boardDTO.getBoardFileCID();
		for(String fileCid : fileCids) {
			System.out.println(fileCid);
			boardMapper.setBoardFile(boardNo, fileCid);
		}
	}

	@Override
	public List<BoardDTO> boardList() {
		return boardMapper.boardList();
	}

	@Override
	public BoardDTO boardDetail(int boardNo) {
		return boardMapper.boardDetail(boardNo);
	}

	@Override
	public void boardDelete(int boardNo) {
		boardMapper.boardDelete(boardNo);
	}

	@Override
	public List<BoardDTO> userBoardList(String userId) {
		return boardMapper.userBoardList(userId);
	}

	@Override
	public List<String> getBoardImgById(int boardNo) {
		return boardMapper.getBoardImgById(boardNo);
	}
}
