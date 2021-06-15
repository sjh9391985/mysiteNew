package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.web.main.MainActionFactory;
import com.douzone.mysite.web.user.UserActionFactory;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class MainController extends HttpServlet {
	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config");
		System.out.println("MainController.init() called: " + configPath);
		super.init();
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// EncodingFilter로 대체
		// request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		Action action = new MainActionFactory().getAction(actionName);
		action.excute(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}