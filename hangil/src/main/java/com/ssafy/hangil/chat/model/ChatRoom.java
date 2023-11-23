package com.ssafy.hangil.chat.model;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ChatRoom {
	
	private String roomId;
	private String user1Id;
	private String user2Id;

}
