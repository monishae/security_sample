package com.emc.it.ooa.sample.xss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.esapi.ESAPI;
import org.springframework.web.filter.DelegatingFilterProxy;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	private static Pattern[] patterns = new Pattern[] {

			// Script fragments

			Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),

			// src='...'

			Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL),

			Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL),

			// lonely script tags

			Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),

			Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),

			// eval(...)

			Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),

			// expression(...)

			Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),

			// javascript:...

			Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),

			// vbscript:...

			Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),

			// onload(...)=...

			Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL)

	};

	public XSSRequestWrapper(HttpServletRequest servletRequest) {
		
		super(servletRequest);

	}

	@Override
	public String[] getParameterValues(String parameter) {
		
		System.out.println("Request wrapper...");

		String[] values = super.getParameterValues(parameter);

		if (values == null) {

			return null;

		}

		int count = values.length;

		String[] encodedValues = new String[count];

		for (int i = 0; i < count; i++) {

			encodedValues[i] = stripXSS(values[i]);

		}

		return encodedValues;

	}

	@Override
	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);

		return stripXSS(value);

	}

	@Override
	public String getHeader(String name) {

		String value = super.getHeader(name);

		return stripXSS(value);

	}

	private String stripXSS(String value) {
		
		System.out.println("Input value : "+value);

		if (value != null) {

			value = ESAPI.encoder().canonicalize(value);

            
			// NOTE: It's highly recommended to use the ESAPI library and
			// uncomment the following line to

			// avoid encoded attacks.

			// value = ESAPI.encoder().canonicalize(value);

			// Avoid null characters

			value = value.replaceAll("", "");

			// Remove all sections that match a pattern

			for (Pattern scriptPattern : patterns) {

				value = scriptPattern.matcher(value).replaceAll("");
				
							
			}
			System.out.println("Output value : "+value);
			
		}
		return value;

	}

}