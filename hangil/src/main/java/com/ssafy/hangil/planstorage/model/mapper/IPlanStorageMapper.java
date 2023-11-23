package com.ssafy.hangil.planstorage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hangil.planstorage.model.PlanStorageDTO;

@Mapper
public interface IPlanStorageMapper {
	
	PlanStorageDTO detailPlan(String planStorageNo);
	
	void registPlan(PlanStorageDTO planstorageDTO);
	
	void deleltePlan(int planStorageNo);
	
	void updatePlan(String planStorageName, int boardNo);
	
	int sharePlan(int planStorageNo);

	void setBoardNo(int boardNo, int planStorageNo);

	List<PlanStorageDTO> getPlanStorageList();

	List<Integer> getPlanList(int planStorageNo);
	
}
