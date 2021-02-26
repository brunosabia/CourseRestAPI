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
		
		/* CONTEUDO DA CLASSE 59
		configurer.favorParameter(false) 
		.ignoreAcceptHeader(false) //ele não irá ignorar o que vier no Header da requisição
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types.
		.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		configurer.favorPathExtension(false) //nesse codigo adicionado basicamente ele diz que nao aceita mais via Extension.
		.favorParameter(true) //tornamos verdadeira a necessidade de um parametro que irá receber o tipo de content
		.parameterName("mediaType") //demos o nome para esse parametro
		.ignoreAcceptHeader(true) //ele irá ignorar o que vier no Header da requisição
		.useRegisteredExtensionsOnly(false) //caso nenhum media type seja add, ele ainda assim rodará a app seguindo o defaultContentType.
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types, JSON e XML
		.mediaType("xml", MediaType.APPLICATION_XML);
		
	}
}
