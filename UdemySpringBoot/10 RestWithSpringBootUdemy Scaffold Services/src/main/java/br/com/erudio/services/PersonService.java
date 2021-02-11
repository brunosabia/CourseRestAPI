package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.Exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service 
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		return repository.save(person); //essas conotações são utilizadas a partir do SPRING DATA, para saber mais sobre, pesquisar.
	}
	
	public List<Person> findAll() {			
		return repository.findAll();
	}
	
	public Person findById(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
	}
	
	public Person update(Person p) {
		repository.findById(p.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		Person entity = new Person();
		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		return repository.save(entity) ;
	}
	
		public void delete(Long id) {
			Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
			repository.delete(entity);
		}
	


	
	


	
	
}
