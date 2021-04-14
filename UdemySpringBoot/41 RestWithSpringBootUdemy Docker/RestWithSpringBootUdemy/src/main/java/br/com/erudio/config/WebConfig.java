package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
//@EnableWebMvc  //comentada aula63
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	//CORS - a partir desse metodo todos os recursos da aplicação ficam disponíveis para qualquer origem.
	//funciona apenas com o GET , POST, PUT E DELETE. o PATCH ou OPTIONS não.
	//levando em conta que alguns clients http enviam o HTTP OPTIONS ANTES de comecar a fazer as requisições ao servidor, esta implementação ainda precisará
	//ser ajustada.
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS","HEAD","TRACE","CONNECT"); //ADICIONAMOS esta linha para garantir que os verbos sejam aceitos e nao causem erros para o client.
	}
	
	
	
	@Override //significa que vai sobreescrever o método anterior.
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		/*//CONTEUDO DA CLASSE 59 - Via Extension Param.
		 *
		configurer.favorParameter(false) 
		.ignoreAcceptHeader(false) //ele não irá ignorar o que vier no Header da requisição
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types.
		.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		//CONTEUDO DA AULA 60
		/* VIA QUERY PARAM.
		configurer.favorPathExtension(false) //nesse codigo adicionado basicamente ele diz que nao aceita mais via Extension.
		.favorParameter(true) //tornamos verdadeira a necessidade de um parametro que irá receber o tipo de content
		.parameterName("mediaType") //demos o nome para esse parametro
		.ignoreAcceptHeader(true) //ele irá ignorar o que vier no Header da requisição
		.useRegisteredExtensionsOnly(false) //caso nenhum media type seja add, ele ainda assim rodará a app seguindo o defaultContentType.
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types, JSON e XML
		.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		//CONTEUDO DA AULA 61
		//Via HEADER PARAM.
		configurer.favorPathExtension(false) //nesse codigo adicionado basicamente ele diz que nao aceita mais via Extension.
		.favorParameter(false) //tornamos falsa a necessidade de um parametro que irá receber o tipo de content
		.ignoreAcceptHeader(false) //ele não irá ignorar o que vier no Header da requisição
		.useRegisteredExtensionsOnly(false) //caso nenhum media type seja add, ele ainda assim rodará a app seguindo o defaultContentType.
		.defaultContentType(MediaType.APPLICATION_JSON) //significa que o conteudo padrão será JSON
		.mediaType("json", MediaType.APPLICATION_JSON) //e irá suportar os seguintes media types, JSON e XML
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MEDIA_TYPE_YML);//AQUI ADICIONAMOS O SUPORTE PARA YML
		
	}
}
