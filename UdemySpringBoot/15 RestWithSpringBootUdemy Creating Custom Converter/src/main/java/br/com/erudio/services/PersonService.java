package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.Exception.ResourceNotFoundException;
import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.repository.PersonRepository;

@Service 
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);//recebe o param VO e transforma em entity
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class); //salva a entity no repository e converte em VO novamente
		return vo; //retorna o VO para o client
	}
	public PersonVOV2 createV2(PersonVOV2 person) {
		var entity = converter.convertVOToEntity(person);//recebe o param VO e transforma em entity
		var vo = converter.convertEntityToVO(repository.save(entity)); //salva a entity no repository e converte em VO novamente
		return vo; //retorna o VO para o client
	}
	
	
	
	public List<PersonVO> findAll() {			
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class); //ele vai ate o banco atraves do repos.findAll, traz uma lista de entidades
		// e o Dozer converte essa lista em PersonVO e retorna para o client.
	}
	
	public PersonVO findById(Long id) {
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca no banco a entity
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class); //converte a entity em VO
	}
	
	public PersonVO update(PersonVO p) {
		
		var entity = repository.findById(p.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); //busca entity
		
		entity.setFirstName(p.getFirstName()); //ajusta a entity
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		
		var vo =  DozerConverter.parseObject(repository.save(entity), PersonVO.class); //repos.save salva o obj e o dozer converte em VO novamente para retornar pro client
		return vo;
	}
	
		public void delete(Long id) {
			Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
			repository.delete(entity);
		}
	


	
	


	
	
}
