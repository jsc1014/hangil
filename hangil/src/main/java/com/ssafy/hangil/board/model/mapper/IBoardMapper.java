package com.ssafy.hangil.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hangil.board.model.BoardDTO;

@Mapper
public interface IBoardMapper {

	void boardWrite(BoardDTO boardDTO);

	List<BoardDTO> boardList();

	BoardDTO boardDetail(int boardNo);

	void boardDelete(int boardNo);

	List<BoardDTO> userBoardList(String userId);

	int getBoardNo(BoardDTO boardDTO);

	List<String> getBoardImgById(int boardNo);

	void setBoardFile(int boardNo, String boardFileCid);

	void setHashTags(int boardNo, String hashTagContent);

	List<BoardDTO> getBoardList(int limit, int offset);

	List<String> getBoardFileCid(int boardNo);

	List<String> getHashTagContent(int boardNo);

	Integer getBoardStorage(String userId);

	int setBoardStorage(String userId);

	void setBoardStorageContent(int boardStorageNo, int boardNo);

	List<Integer> getBoardStorageContent(Integer boardStorageNo);

	BoardDTO getBoardByBoardNo(Integer boardNo);

	List<BoardDTO> getMyBoardList(String userId);

	List<Integer> getSearchBoard(String word);

	void boardStorageContentDelete(Integer boardStorageNo, int boardNo);
}
