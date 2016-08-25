package com.markliu.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 针对 ItemsException 异常的全局异常处理器
 * Author: markliu
 * Time  : 16-8-25 下午6:28
 */
public class GlobalItemsExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
										 HttpServletResponse response,
										 Object handler, Exception ex) {

		String message;
		// 如果是自定义异常，给出具体的异常信息
		if (ex instanceof ItemsException) {
			message = ex.getMessage();
		} else {
			message = "服务器未知错误";
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error_message", message);
		modelAndView.setViewName("items/error");
		return modelAndView;
	}
}
