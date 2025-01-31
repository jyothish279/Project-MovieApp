package com.stackroute.favmovie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDoc {
@Bean
	
	public Docket apiDoc() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.favmovie"))
				.build()
				.apiInfo(apicustomInfo())
				.useDefaultResponseMessages(false);
		
	}
	
	
	public ApiInfo apicustomInfo()
	{
		
		ApiInfoBuilder apibuild=new ApiInfoBuilder();
		
		apibuild.title("Movie App")
		.version("Version 9").license("jyothish.nair@ust-global.com").description("This is an app for Mongo repo");
		
		return apibuild.build();
		
	}


}
