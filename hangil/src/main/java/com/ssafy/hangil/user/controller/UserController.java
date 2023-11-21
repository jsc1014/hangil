package com.ssafy.hangil.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hangil.user.model.UserDTO;
import com.ssafy.hangil.user.model.service.IUserService;
import com.ssafy.hangil.util.JWTUtil;

import lombok.RequiredArgsConstructor;

// 어떤 서버를 쓸지(주소)
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final IUserService iUserService;
	private JWTUtil jwtUtil;

	public UserController(IUserService iUserService, JWTUtil jwtUtil) {
		this.iUserService = iUserService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping()
	public ResponseEntity<Map<String, Object>> registUser(@RequestBody UserDTO userDTO) {

		iUserService.registUser(userDTO);
		HttpStatus status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(status);
	}

	@PostMapping("login")
	public ResponseEntity<Map<String, Object>> userLogin(@RequestBody UserDTO userDTO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDTO loginUser = iUserService.loginUser(userDTO);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				System.out.println(accessToken);
				System.out.println(refreshToken);

//				발급받은 refresh token을 DB에 저장.
				iUserService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			try {
//				로그인 사용자 정보.
				UserDTO userDto = iUserService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable("userId") String userId) {
		System.out.println(userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			iUserService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDTO userDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");

		if (jwtUtil.checkToken(token)) {
			if (token.equals(iUserService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getUserId());
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> userDelete(@PathVariable String id) {
		iUserService.deleteUser(id);
		return ResponseEntity.ok("ok");
	}

	@PutMapping("{userId}")
	public ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String userId, @RequestBody UserDTO userDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		userDTO.setUserId(userId);
		try {
			iUserService.updateUser(userDTO);
			UserDTO userDto = iUserService.userInfo(userId);
			resultMap.put("userInfo", userDto);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
