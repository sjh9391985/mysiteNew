package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo vo = new UserVo();

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		vo.setEmail(email);
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		
		new UserRepository().update(vo);
		
		Long userNo = authUser.getNo();
		UserVo userVo = new UserRepository().updateAuthUser(userNo);
		session.setAttribute("authUser", userVo);
		
		response.sendRedirect(request.getContextPath());
	}

}
