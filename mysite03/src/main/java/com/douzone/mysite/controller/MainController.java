package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;


@Controller
public class MainController {

	@Autowired
	ServletContext application;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping("")
	public String index(Model model) {
		
		SiteVo siteVo = siteService.getSite();
		model.addAttribute("siteVo", siteVo);
		application.setAttribute("title", siteVo.getTitle());

		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg")
	public String message1() {
		
		return "안녕";
	}

	@ResponseBody
	@RequestMapping("/msg2")
	public Object message2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setEmail("son@gamil.com");
		vo.setName("손재현");
		return vo;
	}
}
