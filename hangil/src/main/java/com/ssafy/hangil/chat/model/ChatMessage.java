package com.ssafy.hangil.chat.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ChatMessage {

	private String content;
	private String userId;
	private boolean isCurrentUser;
}
