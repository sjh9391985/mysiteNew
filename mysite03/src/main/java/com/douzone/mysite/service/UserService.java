package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean join(UserVo vo) {
			
		return userRepository.insert(vo);
	}

	public UserVo getUser(String email, String password, String role) {
		
		return userRepository.findByEmailAndPassword(email, password, role);
		
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
		
	}

	public void updateUser(UserVo userVo) {
		userRepository.update(userVo);
		
	}

	public UserVo getUser(String email) {
	
		return userRepository.findByEmail(email);
	}
	
}
