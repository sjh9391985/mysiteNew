package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping({ "", "/{p}","/search","/search/{p}"})
	public String list(@PathVariable(value = "p", required = false) String page, HttpServletRequest request, Model model) {
		
		/* search controller */
		String keyword = request.getParameter("kwd");
		if(keyword==null) {
			keyword="";
		}
		if (page == null) {
			page = "1";
		}
		
		/* listPage */
		List<BoardVo> list = boardService.findByPage(keyword,Integer.parseInt(page));
		Map<String, Integer> map = boardService.informationPage(keyword,page);
		model.addAttribute("informationPage", map);
		model.addAttribute("list", list);
		model.addAttribute("kwd", keyword);
		return "board/list";
	}

	@RequestMapping(value={"/view/{no}/{p}","view/{no}"})
	public String view(@PathVariable(value = "no", required = true) Long boardNo,
			@PathVariable(value = "p", required = false) String page, Model model) {
		BoardVo vo = boardService.view(boardNo);
		model.addAttribute("board", vo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeform() {

		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, HttpServletRequest request) {
		BoardVo vo = new BoardVo();
		Long userNo = authUser.getNo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int groupNo = boardService.findMaxGroupNo();
		
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);
		vo.setGroupNo(groupNo + 1);
		vo.setOrderNo(0);
	

		String no = String.valueOf(boardService.insert(vo));

		return "redirect:/board/view/"+no;
	}
	
	@Auth
	@RequestMapping(value={"/update/{no}","update/{no}/{p}"}, method=RequestMethod.GET)
	public String updateForm(@PathVariable("no") Long no, @PathVariable(value="p",required = false) String page, Model model) {
		BoardVo vo = boardService.findByNo(no);		
		model.addAttribute("board", vo);
		return "board/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpServletRequest request) {
		Long no = Long.valueOf(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(content);
		
		boardService.update(vo);
		return "redirect:/board/view/"+no;
	}
	
	@Auth
	@RequestMapping("/delete/{no}/{p}")
	public String delete(@PathVariable("no") Long no, @PathVariable("p") String page) {
		boardService.delete(no);
		return "redirect:/board/"+page;
	}
	

}
