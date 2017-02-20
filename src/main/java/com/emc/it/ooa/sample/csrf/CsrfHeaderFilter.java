/**
 * 
 */
package com.emc.it.ooa.sample.csrf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * @author elumam
 *
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {

/*	 (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
		        .getName());
		    if (csrf != null) {
		      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
		      String token = csrf.getToken();
		      if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
		        cookie = new Cookie("XSRF-TOKEN", token);
		        cookie.setPath("/");
		        response.addCookie(cookie);
		        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		        response.setHeader("Pragma", "no-cache"); 
		        response.setDateHeader("Expires", 0);  
		        System.out.println("token:" + token);
		      }
		    }
		    filterChain.doFilter(request, response);
	}

}
