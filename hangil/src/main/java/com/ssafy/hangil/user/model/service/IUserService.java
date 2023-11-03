package com.ssafy.hangil.user.model.service;

import com.ssafy.hangil.user.model.UserDTO;

public interface IUserService {
	UserDTO loginUser(UserDTO userDTO);

	void registUser(UserDTO userDTO);
	
	void deleteUser(String userId);
	
	void updateUser(UserDTO userDTO);
}
