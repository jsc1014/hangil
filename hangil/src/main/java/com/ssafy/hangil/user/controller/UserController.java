package com.ssafy.hangil.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.user.model.UserDTO;
import com.ssafy.hangil.user.model.service.IUserService;

// 어떤 서버를 쓸지(주소)
@CrossOrigin("*")

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final IUserService iUserService;
	public UserController(IUserService iUserService) {
		this.iUserService = iUserService;
	}
	
	@PostMapping()
	public ResponseEntity<String> registUser(@RequestBody UserDTO userDTO){
		iUserService.registUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("{id}")
	public ResponseEntity<String> userLogin(@PathVariable String id, @RequestBody UserDTO userDTO){
		iUserService.loginUser(userDTO);
		if(userDTO.getUserPw() != null) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return null;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> userDelete(@PathVariable String id) {
		iUserService.deleteUser(id);
		return ResponseEntity.ok("ok");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> updateBoard(@PathVariable String id, @RequestBody UserDTO userDTO){
		userDTO.setUserId(id);
		iUserService.updateUser(userDTO);
		System.out.println(userDTO);
		return ResponseEntity.ok("OK");
	}
	
}
/*
 * 
 * 
 * GET 		/api/board 		: 전체조회 
 * GET 		/api/board/10 	: 10번 게시물 조회 
 * Post 	/api/board 		: 게시글 등록
 * Delete 	/api/board/10 	: 10번 게시물 삭제 
 * PUT 		/api/board/10 	: 10번 게시물 수정
 * 
 * 
 */