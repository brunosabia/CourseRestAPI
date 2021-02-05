package br.com.erudio.services;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service //essa annotation serve para o Spring cuide da injeção de dependencia dessa classe, sempre que necessário
public class PersonService {

	private final AtomicLong counter = new AtomicLong(); //counter criado para simular um id do BD, sempre criando um Id novo
	
	
	public Person findById(String id) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Bruno");
		person.setLastName("Sabia");
		person.setAddress("SP");
		person.setGender("Male");
		return person ;
	}
	
}
