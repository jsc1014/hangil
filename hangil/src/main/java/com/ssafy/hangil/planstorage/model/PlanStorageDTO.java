package com.ssafy.hangil.planstorage.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlanStorageDTO {
	private int planStorageNo;
	private String userId;
	private String planStorageName;
	// boardNo를 넣어놓고 이걸 이용해서 조회
	private List<Integer> planStorageContents;
}
