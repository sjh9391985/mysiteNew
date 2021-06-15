package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.mysite.web.main.MainAction;
import com.douzone.mysite.web.user.JoinAction;
import com.douzone.mysite.web.user.JoinFormAction;
import com.douzone.mysite.web.user.JoinSuccessAction;
import com.douzone.mysite.web.user.UserActionFactory;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// EncodingFilter로 대체
		// request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		Action action = new UserActionFactory().getAction(actionName);
		action.excute(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}