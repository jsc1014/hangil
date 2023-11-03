package com.ssafy.hangil.storage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.storage.model.service.IStorageService;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("storage")
public class StorageController {

//	private final IStorageService storageService;

	// 사용자 별 저장된 게시글 보관함
	@PostMapping
	public ResponseEntity<?> boardStorage() {
		
		return ResponseEntity.ok("OK");
	}
}
