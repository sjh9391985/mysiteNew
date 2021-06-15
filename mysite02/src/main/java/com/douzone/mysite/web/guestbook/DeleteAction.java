package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String num = request.getParameter("no");
		String pw = request.getParameter("password");

		long number = Long.valueOf(num).longValue();

		GuestBookVo vo = new GuestBookVo();

		vo.setNo(number);
		vo.setPassword(pw);
		new GuestBookRepository().delete(vo);

		MvcUtils.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
	}

}
