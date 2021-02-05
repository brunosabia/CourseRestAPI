package br.com.erudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
		
		@Autowired
		private PersonService services;
		
		@RequestMapping(value="/{id}",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)  
		public Person findById(@PathVariable("id")String id){
			return services.findById(id);//retornando a soma	
		}
		
}


//http://localhost:8080/person/1


//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O