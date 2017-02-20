/**
 * 
 */
package com.emc.it.ooa.sample;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emc.it.ooa.sample.xss.XSSFilterNew;

/**
 * @author elumam
 *
 */
@Configuration
public class WebAppConfiguration {

	
	/*@Bean(name="xpathFilter")
	public XpathFilter getXpathFilter(){
		return new XpathFilter();
	}*/
	
/*	@Bean(name="xssFilter")
	public XSSFilterNew getFilterNew(){
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		XSSFilterNew xssFilter = new XSSFilterNew();
	        registrationBean.setFilter(xssFilter);
	        registrationBean.setOrder(2);
	       // return registrationBean;
	        
		return new XSSFilterNew();
	}
	
	@Bean(name="sqlFilter")
	public SQLFilter getSqlFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	       SQLFilter sqlFilter = new SQLFilter();
	        registrationBean.setFilter(sqlFilter);
	        registrationBean.setOrder(2);
	        //return registrationBean;
	        return sqlFilter;
	}
	*/
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		XSSFilterNew xssFilter = new XSSFilterNew();
        registrationBean.setFilter(xssFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
		return registrationBean;
	}
	
	
	
}
