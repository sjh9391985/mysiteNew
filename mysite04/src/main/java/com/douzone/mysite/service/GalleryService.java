package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository; 
	
	public List<GalleryVo> getGallery() {
		// TODO Auto-generated method stub
		return galleryRepository.getGallery();
	}

	public void upload(GalleryVo vo) {
		galleryRepository.upload(vo);
	}
	
	public void delete(long no) {
		galleryRepository.delete(no);
	}

}