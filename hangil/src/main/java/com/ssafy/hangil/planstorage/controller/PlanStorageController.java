package com.ssafy.hangil.planstorage.controller;

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
	
	// 계획 목록
	@GetMapping("list")
	public ResponseEntity<Map<String, Object>> planList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<PlanStorageDTO> planStorageList = planStorageService.getPlanStorageList();
			resultMap.put("planStorageList", planStorageList);
			System.out.println(planStorageList);
			status = HttpStatus.OK;
		} catch (Exception e) {

		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

    @GetMapping("{planStorageNo}")
    public ResponseEntity<PlanStorageDTO> getPlan(@PathVariable String planStorageNo) {
        PlanStorageDTO storageDTO = planStorageService.detailPlan(planStorageNo);
        System.out.println("카드 선택" + storageDTO);
        return ResponseEntity.ok(storageDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> addPlan(@RequestBody PlanStorageDTO planstorageDTO) {
    	System.out.println(" 카드 저장 " + planstorageDTO);
    	planStorageService.registPlan(planstorageDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("plan")
    public ResponseEntity<Void> updatePlan(@RequestBody PlanStorageDTO planstorageDTO) {
    	planStorageService.updatePlan(planstorageDTO);
    	 System.out.println(" 카드 업데이트 " + planstorageDTO);
    	return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("{planStorageNo}")
    public ResponseEntity<Void> deletePlan(@PathVariable int planStorageNo) {
    	planStorageService.deleltePlan(planStorageNo);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/share/{planStorageNo}")
    public ResponseEntity<Integer> sharePlan(@PathVariable int planStorageNo) {
        int shareId = planStorageService.sharePlan(planStorageNo);
        return ResponseEntity.ok(shareId);
    }
}
