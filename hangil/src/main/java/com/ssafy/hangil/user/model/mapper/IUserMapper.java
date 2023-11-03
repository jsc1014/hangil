package com.ssafy.hangil.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hangil.user.model.UserDTO;

@Mapper
public interface IUserMapper {
	UserDTO loginUser(UserDTO userDTO);

	void registUser(UserDTO userDTO);
	
	void delelteUser(String userId);
	
	void updateUser(UserDTO userDTO);
}
