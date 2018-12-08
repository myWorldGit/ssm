package com.lanpangzi.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptors implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object Obj= request.getSession().getAttribute("role");
		if(Obj==null) {
			request.setAttribute("msg", "  没登陆哦");
			request.getRequestDispatcher("/admin/login").forward(request, response);
			return false;
		}
		if(Obj instanceof String) {
			String role = (String)Obj;
			if(role!=null && role.equals("admin")) {
				return true;
			}else if(role!=null && role.equals("member")){
				response.sendRedirect("/admin/limu");
				return false; 
			}
		}
		request.setAttribute("msg", "权限不足哦");
		request.getRequestDispatcher("/admin/login").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("post");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
