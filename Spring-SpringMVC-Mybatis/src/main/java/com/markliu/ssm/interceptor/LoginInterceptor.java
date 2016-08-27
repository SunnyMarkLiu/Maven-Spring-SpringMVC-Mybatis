package com.markliu.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录验证拦截器
 * Author: markliu
 * Time  : 16-8-27 上午9:32
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * Intercept the execution of a handler. Called after HandlerMapping determined an
	 * appropriate handler object, but before HandlerAdapter invokes the handler.
	 */
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) throws Exception {

		System.out.println("LoginInterceptor preHandle...");

		// 对于不需要登录验证的 url (公开url)直接放行 return true
		String requestURL = request.getRequestURI();
		System.out.println("requestURL: " + requestURL);

		// 此处对公开url硬编码，实际应该将公开url配置到配置文件中
		if (requestURL.contains("login") || requestURL.contains("index")) {
			return true;
		}
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) return true;

		// 服务器内部请求转发打牌到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

	/**
	 * Intercept the execution of a handler. Called after HandlerAdapter actually
	 * invoked the handler, but before the DispatcherServlet renders the view.
	 * Can expose additional model objects to the view via the given ModelAndView.
	 * 在调用 Handler 方法之后，返回 ModelAndView 之前拦截，可用于在ModelAndView 中设置全局模型数据
	 */
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response,
						   Object handler,
						   ModelAndView modelAndView) throws Exception {

		System.out.println("LoginInterceptor postHandle...");
	}

	/**
	 * Callback after completion of request processing, that is, after rendering
	 * the view. Will be called on any outcome of handler execution, thus allows
	 * for proper resource cleanup or **log process**.
	 * Handler 方法执行结束返回 modelAndView 之后执行，可进行相关 cleanup 或全局的日志记录操作。
	 * 全局的日志记录操作时，需要将该拦截器放置到配置的全局拦截器链的第一个位置
	 */
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response,
								Object handler,
								Exception ex) throws Exception {

		System.out.println("LoginInterceptor afterCompletion...");
	}
}
