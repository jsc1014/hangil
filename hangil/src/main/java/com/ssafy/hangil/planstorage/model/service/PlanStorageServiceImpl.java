package com.ssafy.hangil.planstorage.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.hangil.board.model.BoardDTO;
import com.ssafy.hangil.board.model.mapper.IBoardMapper;
import com.ssafy.hangil.planstorage.model.PlanStorageDTO;
import com.ssafy.hangil.planstorage.model.mapper.IPlanStorageMapper;

@Service
public class PlanStorageServiceImpl implements IPlanStorageService {

	final private IPlanStorageMapper storageMapper;
	final private IBoardMapper boardMapper;

	public PlanStorageServiceImpl(IPlanStorageMapper iStorageMapper, IBoardMapper boardMapper) {
		this.storageMapper = iStorageMapper;
		this.boardMapper = boardMapper;
	}

	@Override
	public PlanStorageDTO detailPlan(String planStorageNo) {
		return storageMapper.detailPlan(planStorageNo);
	}

	@Override
	public void registPlan(PlanStorageDTO planstorageDTO) {
		// 저장소 생성
		storageMapper.registPlan(planstorageDTO);
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for (int boardNo : boardNos) {
			storageMapper.setBoardNo(boardNo, planstorageDTO.getPlanStorageNo());
		}
	}

	@Override
	public void updatePlan(PlanStorageDTO planstorageDTO) {
		storageMapper.updatePlanStorageName(planstorageDTO.getPlanStorageNo(), planstorageDTO.getPlanStorageName());
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		Map<String, Object> params = new HashMap<>();
		params.put("planStorageDTO", planstorageDTO);
		storageMapper.deleteAllPlan(planstorageDTO.getPlanStorageNo());
		for (int boardNo : boardNos) {
			params.put("boardNo", boardNo);
			storageMapper.setBoardNo(boardNo, planstorageDTO.getPlanStorageNo());
		}
	}

	@Override
	public void deletePlanStorage(int planStorageNo) {
		storageMapper.deletePlanStorage(planStorageNo);
	}

	@Override
	public void setStoragePlans(PlanStorageDTO planstorageDTO, int storageNo) {
		List<Integer> boardNos = planstorageDTO.getPlanStorageContents();
		for (int boardNo : boardNos) {
			storageMapper.setBoardNo(boardNo, storageNo);
		}
	}

	@Override
	public List<PlanStorageDTO> getPlanStorageList() {
		List<PlanStorageDTO> planStorageDTOs = storageMapper.getPlanStorageList();
		for (PlanStorageDTO planStorageDTO : planStorageDTOs) {
			int planStorageNo = planStorageDTO.getPlanStorageNo();
			List<Integer> storageList = storageMapper.getPlanList(planStorageNo);
			planStorageDTO.setPlanStorageContents(storageList);
		}
		return planStorageDTOs;
	}

	@Override
	public List<BoardDTO> getPlanLists(int planStorageNo) {
		List<BoardDTO> planList = new ArrayList<BoardDTO>();
		List<Integer> boardNoList = storageMapper.getPlanList(planStorageNo);
		// boardNo 리스트 받아옴
		for (int boardNo : boardNoList) {
			planList.add(boardMapper.getBoardByBoardNo(boardNo));
		}
		return planList;
	}

	@Override
	public void deleteAllPlans(int planStorageNo) {
		storageMapper.deletePlanStorage(planStorageNo);
	}
}
