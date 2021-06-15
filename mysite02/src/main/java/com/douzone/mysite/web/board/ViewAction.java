package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ViewAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.valueOf(request.getParameter("no"));
		
					 new BoardRepository().hit(no);	
		BoardVo vo = new BoardRepository().findView(no);
					


		request.setAttribute("boardview", vo);
		
		MvcUtils.forward("board/view", request, response);
		
	}

}
