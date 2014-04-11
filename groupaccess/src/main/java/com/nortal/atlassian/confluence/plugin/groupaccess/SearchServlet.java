package com.nortal.atlassian.confluence.plugin.groupaccess;

import java.io.IOException;
import java.net.URI;

import com.atlassian.templaterenderer.TemplateRenderer;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserKey;
import com.atlassian.sal.api.user.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	private final UserManager userManager;
	private final LoginUriProvider loginUriProvider;
	private final TemplateRenderer renderer;

	public SearchServlet(UserManager userManager, LoginUriProvider loginUriProvider, TemplateRenderer renderer) {
		this.userManager = userManager;
		this.loginUriProvider = loginUriProvider;
		this.renderer = renderer;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserKey userKey = userManager.getRemoteUserKey(request);
		userManager.isSystemAdmin(userKey);
		if (userKey == null || !userManager.isSystemAdmin(userKey)) {
			redirectToLogin(request, response);
			return;
		}
		
		response.setContentType("text/html;charset=utf-8");
		renderer.render("search.vm", response.getWriter());
		//renderer.renderTo(response.getWriter(), "groupaccess", "search.vm", null);
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(loginUriProvider.getLoginUri(getUri(request)).toASCIIString());
	}

	private URI getUri(HttpServletRequest request) {
		StringBuffer builder = request.getRequestURL();
		if (request.getQueryString() != null) {
			builder.append("?");
			builder.append(request.getQueryString());
		}
		return URI.create(builder.toString());
	}
}