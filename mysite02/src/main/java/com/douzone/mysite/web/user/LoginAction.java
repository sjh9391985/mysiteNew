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

public class LoginAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserVo userVo = new UserRepository().findByEmailAndPassword(email, password);
		
		if(userVo == null) {
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			MvcUtils.forward("user/loginform", request, response);
			return;
		}
		//인증처리(session 처리)
		
		HttpSession session = request.getSession(true); //true 로 주면 없으면 만들어서 줘라, false는 없으면 null로 줌
		session.setAttribute("authUser", userVo);
		
		//main으로 redirect
		MvcUtils.redirect(request.getContextPath(), request, response);
		
		
		
	}

}
