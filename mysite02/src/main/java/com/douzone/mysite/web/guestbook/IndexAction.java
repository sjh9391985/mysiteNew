package com.douzone.mysite.web.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class IndexAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GuestBookVo> list = new GuestBookRepository().findAll();

		request.setAttribute("list", list);
		
		//MvcUtils.forward("guestbook/list", request, response);
		// 3. view 포워딩
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
					rd.forward(request, response);
	}

}
