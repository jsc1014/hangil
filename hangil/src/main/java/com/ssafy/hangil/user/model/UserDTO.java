package com.ssafy.hangil.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {

	private String userId;
	private String userPw;
	private String userName;
	private String userNickname;
	private String profilePicture;
	private String token;

}
