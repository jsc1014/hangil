	package com.ssafy.hangil.board.model;

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

}
