package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		//caso não especifique o value pro mapping, ele vai cair direto neste método quando entrar no "/person"
		@RequestMapping(method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)  
		public List<Person> findAll(){
			return services.findAll();	
		}
		
		@RequestMapping(value="/{id}",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)  
		public Person findById(@PathVariable("id")Long id){
			return services.findById(id);	
		}
		
		@RequestMapping(method=RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,  
				produces = MediaType.APPLICATION_JSON_VALUE)  
		public Person create(@RequestBody Person person){
			return services.create(person);	
		}
		
		
		@RequestMapping(method=RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE,  
				produces = MediaType.APPLICATION_JSON_VALUE)  
		public Person update(@RequestBody Person person){
			return services.update(person);	
		}
		
		
		@RequestMapping(value="/{id}",
				method=RequestMethod.DELETE)  
		public void delete(@PathVariable("id")Long id){
			 services.delete(id);	
		}
		
		
}


//http://localhost:8080/person/1


//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O