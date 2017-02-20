package com.emc.it.ooa.sample.sqlinj;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SQLRequestWrapper extends HttpServletRequestWrapper {

	private static Pattern[] patterns = new Pattern[] {

			// SQL Statements

			/*Pattern.compile("\\s{2,0}",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL),
*/
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

	public SQLRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);

		if (values == null) {

			return null;

		}

		int count = values.length;

		String[] encodedValues = new String[count];

		for (int i = 0; i < count; i++) {

			encodedValues[i] = stripSQL(values[i]);

		}

		return encodedValues;

	}

	@Override
	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);

		return stripSQL(value);

	}

	@Override
	public String getHeader(String name) {

		String value = super.getHeader(name);

		return stripSQL(value);

	}

	private String stripSQL(String value) {
		if (value != null) {

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
			System.out.println(value);

		}
		return value;

	}

}
