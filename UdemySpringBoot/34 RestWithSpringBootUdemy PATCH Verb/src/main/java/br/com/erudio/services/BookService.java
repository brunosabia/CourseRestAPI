package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.Exception.ResourceNotFoundException;
import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.repository.BookRepository;

@Service 
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);//recebe o param VO e transforma em entity
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class); //salva a entity no repository e converte em VO novamente
		return vo; //retorna o VO para o client
	}
	
	public List<BookVO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class); //busca a lista de entities e retorna convertendo em VO
	}

	public BookVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca no banco a entity
		return DozerConverter.parseObject(repository.save(entity), BookVO.class); //converte a entity em VO
	}
	
	public BookVO update(BookVO p) {
		
		var entity = repository.findById(p.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca entity
		
		entity.setAuthor(p.getAuthor()); //ajusta a entity
		entity.setTitle(p.getTitle());
		entity.setLaunch_date(p.getLaunch_date());
		entity.setPrice(p.getPrice());
		
		var vo =  DozerConverter.parseObject(repository.save(entity), BookVO.class); //repos.save salva o obj e o dozer converte em VO novamente para retornar pro client
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca a entity no banco
		repository.delete(entity); //deleta
	}
}
