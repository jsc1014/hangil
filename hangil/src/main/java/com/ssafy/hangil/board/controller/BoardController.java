package com.ssafy.hangil.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.board.model.service.IBoardService;
import com.ssafy.hangil.user.model.UserDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BoardController {

	private final IBoardService boardService;

	// 게시글 작성
	@PostMapping("write")
	public ResponseEntity<Map<String, Object>> boardWrite(@RequestBody BoardDTO boardDTO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boardService.boardWrite(boardDTO);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(status);
	}

	// 게시글 목록
	@GetMapping("list")
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "5") int limit) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<BoardDTO> boardList = boardService.getBoardList(page, limit);
			resultMap.put("boardList", boardList);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("save")
	public ResponseEntity<Map<String, Object>> boardSave(@RequestParam String userId, @RequestParam int boardNo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boardService.boardSave(userId, boardNo);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", "이미 저장 되었습니다");
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 게시글 상세 정보
	@GetMapping("{boardNo}")
	public ResponseEntity<?> boardDetail(@PathVariable int boardNo) {
		System.out.println(boardNo);
		BoardDTO board = boardService.boardDetail(boardNo);
		return ResponseEntity.status(HttpStatus.OK).body(board);
	}

	// 게시글 삭제
	@DeleteMapping("{boardNo}")
	public ResponseEntity<?> boardDelete(@PathVariable int boardNo) {
		boardService.boardDelete(boardNo);
		return ResponseEntity.ok("OK");
	}

	// 사용자가 등록한 게시글 불러오기
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> userBoardList(@PathVariable String userId) {
		System.out.println(userId);
		List<BoardDTO> list = boardService.userBoardList(userId);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
