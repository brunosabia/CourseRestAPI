package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
		
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
