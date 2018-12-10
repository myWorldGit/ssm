package com.lanpangzi.controller.admin;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler()
	public ModelAndView handleException(Exception e) {
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		model.addObject("error", e.getMessage());
		return model;
	}
}
