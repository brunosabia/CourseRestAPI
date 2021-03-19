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

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", tags = {"BookEndpoint"})
@RestController
@RequestMapping("api/book/v1")
public class BookController {

	@Autowired
	private BookService services;
	
	@ApiOperation(value = "Find all the books recorded")
	@GetMapping(produces = {"application/json", "application/xml" ,"application/x-yaml" })
	public List<BookVO> findAll(){
		
		List<BookVO> books = services.findAll();	
		
		books
		.stream() //vai pegar a lista (persons) vai percorrer um a um com o foreach de acordo com o que especificarmos abaixo:
		.forEach(p -> p.add(
				linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()
				)
			); //no caso irá adicionar a todos os itens da lista um link apontando pra ele mesmo.
		return books;
	}
	
	@ApiOperation(value = "Find specific book recorded")
	@GetMapping(value = "/{id}",produces = {"application/json", "application/xml" ,"application/x-yaml"})
	public BookVO findById(@PathVariable("id")Long id){
		
		BookVO bookVO = services.findById(id); //busca o Person no banco e armazena em personVO.
		
		bookVO.add(linkTo(methodOn(BookController.class) //irá adicionar os links em BookController (neste caso ele linka com ele mesmo)
				.findById(id)) //o método que será linkado é o find by id
				.withSelfRel()); //serve para dizer que relaciona a si mesmo.
		return bookVO;	
	}
	
	
	@ApiOperation(value = "Creates book on the database")
	@PostMapping(produces = {"application/json", "application/xml","application/x-yaml" },
			 consumes = {"application/json", "application/xml","application/x-yaml" }) 
	public BookVO create(@RequestBody BookVO book){
		
		BookVO bookVO = services.create(book);	
		
		bookVO.add(linkTo(methodOn(BookController.class) //irá adicionar os links em BookController(neste caso ele linka com ele mesmo)
				.findById(bookVO.getKey())) //o método que será linkado é o find by id
				.withSelfRel()); //serve para dizer que relaciona a si mesmo.
		return bookVO;
	}
	
	
	@ApiOperation(value = "Update specific book data")
	@PutMapping(produces = {"application/json", "application/xml" ,"application/x-yaml"},
			 consumes = {"application/json", "application/xml","application/x-yaml" }) 
	public BookVO update(@RequestBody BookVO book){
		
		BookVO bookVO = services.update(book);
		
		bookVO.add(linkTo(methodOn(BookController.class) //irá adicionar os links em BookController(neste caso ele linka com ele mesmo)
				.findById(bookVO.getKey())) //o método que será linkado é o find by id
				.withSelfRel()); //serve para dizer que relaciona a si mesmo.
		return bookVO;		
	}
	
	@ApiOperation(value = "Delete book recorded")
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> delete(@PathVariable("id")Long id){
		 services.delete(id);	
		 return ResponseEntity.ok().build();  //Adicionamos um retorno para o delete tb
	}
}




//http://localhost:8080/api/book/v1