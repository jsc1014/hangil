package com.ssafy.hangil.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.board.model.mapper.IBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements IBoardService {

	private final IBoardMapper boardMapper;

	@Override
	public void boardWrite(BoardDTO boardDTO) {
		boardMapper.boardWrite(boardDTO);
		int boardNo = boardMapper.getBoardNo(boardDTO);
		List<String> boardFileCids = boardDTO.getBoardFileCid();
		for (String boardFileCid : boardFileCids) {
			boardMapper.setBoardFile(boardNo, boardFileCid);
		}

		List<String> hashTagContents = boardDTO.getHashTagContent();
		for (String hashTagContent : hashTagContents) {
			boardMapper.setHashTags(boardNo, hashTagContent);
		}

	}

	@Override
	public int getBoardNo(BoardDTO boardDTO) {
		return boardMapper.getBoardNo(boardDTO);
	}

	@Override
	public void setBoardFile(BoardDTO boardDTO, int boardNo) {
		List<String> boardFileCids = boardDTO.getBoardFileCid();
		for (String boardFileCid : boardFileCids) {
			System.out.println(boardFileCid);
			boardMapper.setBoardFile(boardNo, boardFileCid);
		}
	}

	@Override
	public void setBoardHashTagContents(BoardDTO boardDTO, int boardNo) {
		List<String> hashTagContents = boardDTO.getHashTagContent();
		for (String hashTagContent : hashTagContents) {
			System.out.println(hashTagContent);
			boardMapper.setHashTags(boardNo, hashTagContent);
		}
	}

	@Override
	public List<BoardDTO> getBoardList(int page, int limit) {
		int offset = (page - 1) * limit;
		List<BoardDTO> boardDTOs = boardMapper.getBoardList(limit, offset);
		for (BoardDTO boardDTO : boardDTOs) {
			int boardNo = boardDTO.getBoardNo();
			boardDTO.setBoardFileCid(boardMapper.getBoardFileCid(boardNo));
			boardDTO.setHashTagContent(boardMapper.getHashTagContent(boardNo));
		}
		return boardDTOs;

	}

	@Override
	public void boardSave(String userId, int boardNo) {
		Integer boardStorageNo = boardMapper.getBoardStorage(userId);
		if (boardStorageNo == null) {
			boardMapper.setBoardStorage(userId);
			boardStorageNo = boardMapper.getBoardStorage(userId);
		}
		boardMapper.setBoardStorageContent(boardStorageNo, boardNo);
	}

	@Override
	public List<BoardDTO> getBoardStorage(String userId) {
		Integer boardStorageNo = boardMapper.getBoardStorage(userId);
		List<BoardDTO> boardList = new ArrayList<>();
		if (boardStorageNo != null) {
			List<Integer> boardNoList = boardMapper.getBoardStorageContent(boardStorageNo);
			for(Integer boardNo : boardNoList) {
				boardList.add(boardMapper.getBoardByBoardNo(boardNo));
			}
		}
		return boardList;
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
