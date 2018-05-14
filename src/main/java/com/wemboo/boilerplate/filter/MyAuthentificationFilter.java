package com.wemboo.boilerplate.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


/**
 * MyAuthenticationFilter Servlet Filter implementation .
 * 
 * @author osh
 */
public class MyAuthentificationFilter extends PassThruAuthenticationFilter {

	/**
	 * Settings
	 * TODO: Read from shiro.ini
	 */
	public static final String LOGIN_PAGE = "login.xhtml";
	public static final String LOGIN_INPUT_NAME = "loginForm:username";
	public static final String PASSWD_INPUT_NAME = "loginForm:passwd";
	

	/**
	 * Logger for the Class.
	 * TODO: Use thru CDI
	 */
	private final Logger log = LoggerFactory.getLogger(MyAuthentificationFilter.class);

	/*
	 * Impl of org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter#
	 * onAccessDenied(javax.servlet. ServletRequest,
	 * javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(final ServletRequest request, final ServletResponse response) throws Exception {

		log.debug("### MyAuthFilter: accessDenied ###");

		final Subject subject = getSubject(request, response);

		final HttpServletRequestWrapper req = new HttpServletRequestWrapper((HttpServletRequest) request);
		final HttpServletResponseWrapper resp = new HttpServletResponseWrapper((HttpServletResponse) response);

		final String user = req.getParameter(LOGIN_INPUT_NAME);
		final String pass = req.getParameter(PASSWD_INPUT_NAME);
		final String URI = req.getRequestURI();

		if (user != null && pass != null && !user.isEmpty() && !pass.isEmpty()) {

			log.debug("Username and pass were FOUND, try to add login token...");

			final UsernamePasswordToken token = new UsernamePasswordToken(user, pass);

			try {
				log.debug("Trying to login...");
				subject.login(token);
				return true;

			} catch (final Exception e) {

				log.debug("Authentification failed");

				if (URI != null && URI.contains(LOGIN_PAGE)) {

					//Login page was requested, allow to see and do not redirect.
					return true;
				}
				else
				{
					//Some other page was requested, do not allow and redirect to login page
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resp.setHeader("X-MESSAGE", "Authentification failed.");
					resp.sendRedirect(LOGIN_PAGE);
					return false;
				}
			}
		} else {
			
			log.debug("Username and pass were not found, redirect to login page");
			if (URI != null && URI.contains(LOGIN_PAGE)) {
				//Login page was requested, allow to see and do not redirect.
				return true;
			} else {

				//Some other page was requested, do not allow and redirect to login page
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.setHeader("X-MESSAGE", "No user or hash provided.");
				resp.sendRedirect(LOGIN_PAGE);
				return false;
			}
		}
	}



}
