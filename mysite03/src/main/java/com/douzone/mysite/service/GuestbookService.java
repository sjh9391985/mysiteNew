package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {
	
	@Autowired
	GuestBookRepository guestbookRepository;
	
	public List<GuestBookVo> getMessageList() {
		return guestbookRepository.findAll();
	}
	

	public void addMessage(GuestBookVo vo) {
		
		guestbookRepository.insert(vo);
		
	}

	public void deleteMessage(Long no, String password) {
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		guestbookRepository.delete(vo);
		
		
	}

}
