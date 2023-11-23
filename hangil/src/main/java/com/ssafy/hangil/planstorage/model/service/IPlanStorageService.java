package com.ssafy.hangil.planstorage.model.service;

import java.util.List;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.planstorage.model.PlanStorageDTO;

public interface IPlanStorageService {
	PlanStorageDTO detailPlan(String planStorageNo);

	void registPlan(PlanStorageDTO planstorageDTO);

	void updatePlan(PlanStorageDTO planstorageDTO);

	void setStoragePlans(PlanStorageDTO planstorageDTO, int boardNo);

	List<PlanStorageDTO> getPlanStorageList();

	void deletePlanStorage(int planStorageNo);

	void deleteAllPlans(int planStorageNo);

	List<BoardDTO> getPlanLists(int planStorageNo);

}
