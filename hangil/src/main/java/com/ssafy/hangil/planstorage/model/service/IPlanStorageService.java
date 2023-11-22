package com.ssafy.hangil.planstorage.model.service;

import com.ssafy.hangil.planstorage.model.PlanStorageDTO;

public interface IPlanStorageService {
	PlanStorageDTO detailPlan(String planStorageNo);
	
	void registPlan(PlanStorageDTO planstorageDTO);
	
	void deleltePlan(int planStorageNo);
	
	void updatePlan(PlanStorageDTO planstorageDTO);
	
	int sharePlan(int planStorageNo);
	
	void setStoragePlans(PlanStorageDTO planstorageDTO, int boardNo);
}