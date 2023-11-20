package com.ssafy.hangil.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.hangil.user.model.UserDTO;
import com.ssafy.hangil.user.model.mapper.IUserMapper;

@Service
public class UserServiceImpl implements IUserService {

	final private IUserMapper iUserMapper;

	public UserServiceImpl(IUserMapper iUserMapper) {
		this.iUserMapper = iUserMapper;
	}

	@Override
	public UserDTO loginUser(UserDTO userDTO) {
		return iUserMapper.loginUser(userDTO);
	}

	@Override
	public void registUser(UserDTO userDTO) {
		iUserMapper.registUser(userDTO);
	}

	@Override
	public void deleteUser(String userId) {
		iUserMapper.delelteUser(userId);
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		iUserMapper.updateUser(userDTO);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userToken", refreshToken);
		iUserMapper.saveRefreshToken(map);
	}
	
	@Override
	public UserDTO userInfo(String userId) {
		return iUserMapper.userInfo(userId);
	}
	
	@Override
	public Object getRefreshToken(String userId) {
		return iUserMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId)  {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		iUserMapper.deleteRefreshToken(map);
	}
}
