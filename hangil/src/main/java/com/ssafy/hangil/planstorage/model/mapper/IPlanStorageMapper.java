package com.ssafy.hangil.planstorage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hangil.planstorage.model.PlanStorageDTO;

@Mapper
public interface IPlanStorageMapper {
	
	PlanStorageDTO detailPlan(String planStorageNo);
	
	void registPlan(PlanStorageDTO planStorageDTO);
	
	void deleltePlan(int planStorageNo);
	
	void updatePlan(PlanStorageDTO planStorageDTO);
	
	int sharePlan(int planStorageNo);

	void setBoardNo(int boardNo, int storageNo);
	
}
