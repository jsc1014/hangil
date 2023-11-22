package com.ssafy.hangil.planstorage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.planstorage.model.PlanStorageDTO;
import com.ssafy.hangil.planstorage.model.service.IPlanStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/plan")
public class PlanStorageController {

	private final IPlanStorageService storageService;

    @GetMapping("{planStorageNo}")
    public ResponseEntity<PlanStorageDTO> getPlan(@PathVariable String planStorageNo) {
        PlanStorageDTO storageDTO = storageService.detailPlan(planStorageNo);
        System.out.println("카드 선택" + storageDTO);
        return ResponseEntity.ok(storageDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> addPlan(@RequestBody PlanStorageDTO planstorageDTO) {
        storageService.registPlan(planstorageDTO);
        System.out.println(planstorageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{planStorageNo}")
    public ResponseEntity<Void> deletePlan(@PathVariable int planStorageNo) {
        storageService.deleltePlan(planStorageNo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("plan")
    public ResponseEntity<Void> updatePlan(@RequestBody PlanStorageDTO planstorageDTO) {
        storageService.updatePlan(planstorageDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/share/{planStorageNo}")
    public ResponseEntity<Integer> sharePlan(@PathVariable int planStorageNo) {
        int shareId = storageService.sharePlan(planStorageNo);
        return ResponseEntity.ok(shareId);
    }
}
