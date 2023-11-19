package com.ssafy.hangil.user.model.service;

import com.ssafy.hangil.user.model.UserDTO;

public interface IUserService {
	UserDTO loginUser(UserDTO userDTO);

	void registUser(UserDTO userDTO);
	
	void deleteUser(String userId);
	
	void updateUser(UserDTO userDTO);

	void saveRefreshToken(String userId, String refreshToken);

	Object getRefreshToken(String userId) throws Exception;
	
	void deleRefreshToken(String userId);

	UserDTO userInfo(String userId);
}
