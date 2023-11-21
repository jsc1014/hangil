	package com.ssafy.hangil.board.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

	private int boardNo;

	private String userId;

	private String boardTitle;

	private String boardContent;

	private int boardLike;

	private double boardLatitude;

	private double boardLongitude;

	private String boardDate;
	
	private List<String> boardFileCid;
	
	private List<String> boardTags;

}
