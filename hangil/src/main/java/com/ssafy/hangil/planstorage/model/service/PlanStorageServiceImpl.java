package com.ssafy.hangil.planstorage.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.planstorage.model.PlanStorageDTO;
import com.ssafy.hangil.planstorage.model.mapper.IPlanStorageMapper;

@Service
public class PlanStorageServiceImpl implements IPlanStorageService {

	final private IPlanStorageMapper storageMapper;

	public PlanStorageServiceImpl(IPlanStorageMapper iStorageMapper) {
		this.storageMapper = iStorageMapper;
	}

	@Override
	public PlanStorageDTO detailPlan(int planStorageNo) {
		PlanStorageDTO dto = storageMapper.detailPlan(planStorageNo);
		System.out.println("조회" + dto);
		return storageMapper.detailPlan(planStorageNo);
	}

	@Override
	public void registPlan(PlanStorageDTO planstorageDTO) {
		// 저장소 생성
		System.out.println(planstorageDTO.getPlanStorageName());
		storageMapper.registPlan(planstorageDTO);
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		System.out.println("보드 번호 " + boardNos);
		for (int boardNo : boardNos) {
			storageMapper.setBoardNo(boardNo, planstorageDTO.getPlanStorageNo());
		}
	}

	@Override
	public void updatePlan(PlanStorageDTO planstorageDTO) {
		System.out.println("서비스 " + planstorageDTO.getPlanStorageName());
		storageMapper.updatePlanStorageName(planstorageDTO.getPlanStorageNo(),planstorageDTO.getPlanStorageName());
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for (int boardNo : boardNos) {
			storageMapper.updatePlan(planstorageDTO, boardNo);
		}
	}

	@Override
	public void deleltePlan(int planStorageNo) {
		storageMapper.deleltePlan(planStorageNo);
	}


	@Override
	public int sharePlan(int planStorageNo) {
		return storageMapper.sharePlan(planStorageNo);
	}

	@Override
	public void setStoragePlans(PlanStorageDTO planstorageDTO, int storageNo) {
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for(int boardNo : boardNos) {
			storageMapper.setBoardNo(boardNo, storageNo);
		}
	}

	@Override
	public List<PlanStorageDTO> getPlanStorageList() {
		List<PlanStorageDTO> planStorageDTOs = storageMapper.getPlanStorageList();
		for(PlanStorageDTO planStorageDTO : planStorageDTOs) {
			int planStorageNo = planStorageDTO.getPlanStorageNo();
			planStorageDTO.setPlanStorageContents(storageMapper.getPlanList(planStorageNo));
		}
		return planStorageDTOs;
	}

}
