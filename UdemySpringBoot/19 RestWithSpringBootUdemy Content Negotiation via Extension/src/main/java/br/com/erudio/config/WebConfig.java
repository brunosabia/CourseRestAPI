package br.com.erudio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Override //significa que vai sobreescrever o método anterior.
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false) 
		.ignoreAcceptHeader(false) //ele não irá ignorar o que vier no Header da requisição
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types.
		.mediaType("xml", MediaType.APPLICATION_XML);
	}
}
