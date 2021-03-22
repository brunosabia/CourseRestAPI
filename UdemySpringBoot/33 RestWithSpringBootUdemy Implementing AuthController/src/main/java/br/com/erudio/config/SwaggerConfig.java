package br.com.erudio.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //para que o configurate scan entenda que esta é uma classe de configuração
@EnableSwagger2  //para habilitar o swagger
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("br.com.erudio"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());//adicionado
		}
		
		public ApiInfo apiInfo() {
			return new ApiInfo("RESTFul API with Spring Boot 2.1.3",
					"Some description about your API",
					"version v1",
					"termsOfServiceUrl",
					new Contact("Org Name", "www.site.com.br", "email@email.com"),
					"license of API",
					"license of URL",
					Collections.emptyList()
					);
		
		
		/* Nestas linhas acima vc descreve que irá criar um documentation do tipo SWAGGER_2
		 * selecionar todos os nossos controlers e tambem todos os nossos paths
		 * 
		 * 
		 * 
		 * http://localhost:8080/v2/api-docs
		 * 
		 * 
		 * http://localhost:8080/swagger-ui.html
		 */
	}
	
}
