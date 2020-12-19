package com.stackroute.favmovie;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.favmovie.filter.JWTFilter;

@SpringBootApplication
public class favouritemovie {

	public static void main(String[] args) {
		
		SpringApplication.run(favouritemovie.class, args);

	}
	
	
	 @Bean public FilterRegistrationBean getFilterbean() {
	 UrlBasedCorsConfigurationSource urlcorsconfig=new
	 UrlBasedCorsConfigurationSource();
	 
	 CorsConfiguration corsconfig=new CorsConfiguration();
	 corsconfig.setAllowCredentials(true); corsconfig.addAllowedHeader("*");
	 corsconfig.addAllowedMethod("*"); corsconfig.addAllowedOrigin("*");
	  urlcorsconfig.registerCorsConfiguration("/**", corsconfig);
	 FilterRegistrationBean filterbean=new FilterRegistrationBean(new
	 CorsFilter(urlcorsconfig)); filterbean.setFilter(new JWTFilter());
	  filterbean.addUrlPatterns("/api/food/addRestaurant",
	  "/api/food/deleteRestaurant"); return filterbean; }
	 
}
