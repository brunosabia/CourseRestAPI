package br.com.erudio.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
		
		@Autowired
		private PersonService services;
		
		//caso não especifique o value pro mapping, ele vai cair direto neste método quando entrar no "/person"
		@GetMapping(produces = {"application/json", "application/xml" ,"application/x-yaml" })
		public List<PersonVO> findAll(){
			return services.findAll();	
		}
		
		@GetMapping(value = "/{id}",produces = {"application/json", "application/xml" ,"application/x-yaml"})
		public PersonVO findById(@PathVariable("id")Long id){
			
			PersonVO personVO = services.findById(id); //busca o Person no banco e armazena em personVO.
			
			personVO.add(linkTo(methodOn(PersonController.class) //irá adicionar os links em PersonController(neste caso ele linka com ele mesmo)
					.findById(id)) //o método que será linkado é o find by id
					.withSelfRel()); //serve para dizer que relaciona a si mesmo.
			return personVO;	
		}
		
		@PostMapping(produces = {"application/json", "application/xml","application/x-yaml" },
					 consumes = {"application/json", "application/xml","application/x-yaml" }) 
		public PersonVO create(@RequestBody PersonVO person){
			return services.create(person);	
		}
		
		
		@PutMapping(produces = {"application/json", "application/xml" ,"application/x-yaml"},
				 consumes = {"application/json", "application/xml","application/x-yaml" }) 
		public PersonVO update(@RequestBody PersonVO person){
			return services.update(person);	
		}
		
		
		@DeleteMapping("/{id}") 
		public ResponseEntity<?> delete(@PathVariable("id")Long id){
			 services.delete(id);	
			 return ResponseEntity.ok().build();  //Adicionamos um retorno para o delete tb
		}
		
		
}


//http://localhost:8080/person/1


//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O