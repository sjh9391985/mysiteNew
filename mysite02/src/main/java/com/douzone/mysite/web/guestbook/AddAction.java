package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class AddAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String content = request.getParameter("content");

		GuestBookVo vo = new GuestBookVo();

		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(content);

		new GuestBookRepository().insert(vo);

		MvcUtils.redirect(request.getContextPath()+"/guestbook?a=list", request, response);

	}

}
