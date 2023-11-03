package com.ssafy.hangil.user.model.service;

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

}
