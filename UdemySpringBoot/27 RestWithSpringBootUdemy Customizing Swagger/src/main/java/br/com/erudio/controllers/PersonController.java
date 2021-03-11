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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", tags = {"PersonEndpoint"})
@RestController
@RequestMapping("api/person/v1")
public class PersonController {
		
		@Autowired
		private PersonService services;
		
		
		@ApiOperation(value = "Find all the people recorded")
		//caso não especifique o value pro mapping, ele vai cair direto neste método quando entrar no "/person"
		@GetMapping(produces = {"application/json", "application/xml" ,"application/x-yaml" })
		public List<PersonVO> findAll(){
			
			List<PersonVO> persons = services.findAll();	
			
			persons
			.stream() //vai pegar a lista (persons) vai percorrer um a um com o foreach de acordo com o que especificarmos abaixo:
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
					)
				); //no caso irá adicionar a todos os itens da lista um link apontando pra ele mesmo.
			return persons;
		}
		
		@ApiOperation(value = "Find specific person recorded")
		@GetMapping(value = "/{id}",produces = {"application/json", "application/xml" ,"application/x-yaml"})
		public PersonVO findById(@PathVariable("id")Long id){
			
			PersonVO personVO = services.findById(id); //busca o Person no banco e armazena em personVO.
			
			personVO.add(linkTo(methodOn(PersonController.class) //irá adicionar os links em PersonController(neste caso ele linka com ele mesmo)
					.findById(id)) //o método que será linkado é o find by id
					.withSelfRel()); //serve para dizer que relaciona a si mesmo.
			return personVO;	
		}
		
		@ApiOperation(value = "Creates person on the database")
		@PostMapping(produces = {"application/json", "application/xml","application/x-yaml" },
					 consumes = {"application/json", "application/xml","application/x-yaml" }) 
		public PersonVO create(@RequestBody PersonVO person){
			PersonVO personVO = services.create(person);	
			personVO.add(linkTo(methodOn(PersonController.class) //irá adicionar os links em PersonController(neste caso ele linka com ele mesmo)
					.findById(personVO.getKey())) //o método que será linkado é o find by id
					.withSelfRel()); //serve para dizer que relaciona a si mesmo.
			return personVO;
		}
		
		@ApiOperation(value = "Update specific person data")
		@PutMapping(produces = {"application/json", "application/xml" ,"application/x-yaml"},
				 consumes = {"application/json", "application/xml","application/x-yaml" }) 
		public PersonVO update(@RequestBody PersonVO person){
			PersonVO personVO = services.update(person);
			personVO.add(linkTo(methodOn(PersonController.class) //irá adicionar os links em PersonController(neste caso ele linka com ele mesmo)
					.findById(personVO.getKey())) //o método que será linkado é o find by id
					.withSelfRel()); //serve para dizer que relaciona a si mesmo.
			return personVO;
				
		}
		
		@ApiOperation(value = "Delete person recorded")
		@DeleteMapping("/{id}") 
		public ResponseEntity<?> delete(@PathVariable("id")Long id){
			 services.delete(id);	
			 return ResponseEntity.ok().build();  //Adicionamos um retorno para o delete tb
		}
		
		
}


//http://localhost:8080/person/1


//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O