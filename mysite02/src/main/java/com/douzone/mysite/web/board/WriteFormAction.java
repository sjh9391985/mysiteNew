package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		
		MvcUtils.forward("board/write", request, response);
		
		
		
	}

}
