package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler 처리하는 경우(정적 자원 접근)	
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method에 @Auth가 없으면 Type에 붙어 있는지 확인한다(#과제)
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		/**
		 * if (auth == null){
		 * 		auth = handlerMethod.
		 * }
		 * 
		 */
		
		// Type이나 Method 둘 다 @Auth가 적용이 안되어 있는 경우
		if(auth == null) {
			return true;
		}
		
		//5. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
			}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//6. 권한 체크를 위해서 @Auth의 role 가져오기("ADMIN", "USER") Admin 으로 login 했을땐 어디든 들어갈 수 있어야 함.
		
		String role = auth.role();
		
		if("USER".equals(role)) {
			return true;
		}
		
		if("ADMIN".equals(authUser.getRole()) == false) {
			System.out.println(authUser.getRole());
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		return true;
	}

}
