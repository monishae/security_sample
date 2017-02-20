package com.emc.it.ooa.sample;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


@Controller
public class HomeController {
	


	@RequestMapping(value="/")
	public String renderHomePage(){
		return "login";
	}

	@RequestMapping(value="/login")
	public String welcomePage(){
		return "loginsuccess";
	}
		
	/*@RequestMapping(value = "/login/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage(Principal p) {
		p.getName();
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page !");
		model.setViewName("admin");
 		return model;
 
	}*/
	
	
	

}
