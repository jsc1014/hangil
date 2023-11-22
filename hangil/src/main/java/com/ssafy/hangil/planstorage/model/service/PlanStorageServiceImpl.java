package com.ssafy.hangil.planstorage.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.hangil.planstorage.model.PlanStorageDTO;
import com.ssafy.hangil.planstorage.model.mapper.IPlanStorageMapper;

@Service
public class PlanStorageServiceImpl implements IPlanStorageService {

	final private IPlanStorageMapper iStorageMapper;

	public PlanStorageServiceImpl(IPlanStorageMapper iStorageMapper) {
		this.iStorageMapper = iStorageMapper;
	}

	@Override
	public PlanStorageDTO detailPlan(String planStorageNo) {
		return iStorageMapper.detailPlan(planStorageNo);
	}

	@Override
	public void registPlan(PlanStorageDTO planstorageDTO) {
		iStorageMapper.registPlan(planstorageDTO);
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for (int boardNo : boardNos) {
			iStorageMapper.setBoardNo(boardNo, planstorageDTO.getPlanStorageNo());
		}
	}

	@Override
	public void deleltePlan(int planStorageNo) {
		iStorageMapper.deleltePlan(planStorageNo);
	}

	@Override
	public void updatePlan(PlanStorageDTO planstorageDTO) {
		iStorageMapper.updatePlan(planstorageDTO);
	}

	@Override
	public int sharePlan(int planStorageNo) {
		return iStorageMapper.sharePlan(planStorageNo);
	}

	@Override
	public void setStoragePlans(PlanStorageDTO planstorageDTO, int storageNo) {
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for(int boardNo : boardNos) {
			iStorageMapper.setBoardNo(boardNo, storageNo);
		}

	}

}
