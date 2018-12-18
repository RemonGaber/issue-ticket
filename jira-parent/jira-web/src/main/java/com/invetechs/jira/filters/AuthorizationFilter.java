package com.invetechs.jira.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			
			if(ses == null || ses.getAttribute("userId") == null){
				if ((reqURI.indexOf("/tickets.xhtml") >= 0) || (reqURI.indexOf("/ticket.xhtml") >= 0) || (reqURI.indexOf("/preview.xhtml") >= 0))
					resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
				else{
					chain.doFilter(request, response);
				}
			}else{
				if ((reqURI.indexOf("/login.xhtml") >= 0) || (reqURI.indexOf("/register.xhtml") >= 0))
					resp.sendRedirect(reqt.getContextPath() + "/tickets.xhtml");
				else
					chain.doFilter(request, response);
					
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
