package com.ssafy.hangil.planstorage.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.planstorage.model.PlanStorageDTO;
import com.ssafy.hangil.planstorage.model.service.IPlanStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/plan")
public class PlanStorageController {

	private final IPlanStorageService planStorageService;

	// 총 계획 목록
	@GetMapping("list")
	public ResponseEntity<Map<String, Object>> planList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		List<PlanStorageDTO> planStorageList = planStorageService.getPlanStorageList();
		resultMap.put("planStorageList", planStorageList);
		status = HttpStatus.OK;

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 계획 저장소 얻기
	@GetMapping("{planStorageNo}")
	public ResponseEntity<PlanStorageDTO> getPlan(@PathVariable String planStorageNo) {
		PlanStorageDTO storageDTO = planStorageService.detailPlan(planStorageNo);
		return ResponseEntity.ok(storageDTO);
	}

	// 계획 저장소에 저장된 계획 리스트 얻기
	@GetMapping("list/{planStorageNo}")
	public ResponseEntity<List<BoardDTO>> getSavedPlanLists(@PathVariable int planStorageNo) {
		List<BoardDTO> planList = new ArrayList<BoardDTO>();
		// 해당 저장소 번호
		planList = planStorageService.getPlanLists(planStorageNo);
		return ResponseEntity.ok(planList);
	}

	@PostMapping()
	public ResponseEntity<Void> addPlan(@RequestBody PlanStorageDTO planstorageDTO) {
		planStorageService.registPlan(planstorageDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping()
	public ResponseEntity<Void> updatePlan(@RequestBody PlanStorageDTO planstorageDTO) {
		planStorageService.updatePlan(planstorageDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{planStorageNo}")
	public ResponseEntity<Void> deletePlan(@PathVariable int planStorageNo) {
		planStorageService.deleteAllPlans(planStorageNo);
		return ResponseEntity.ok().build();
	}

}
