package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service //essa annotation serve para o Spring cuide da injeção de dependencia dessa classe, sempre que necessário
public class PersonService {

	private final AtomicLong counter = new AtomicLong(); //counter criado para simular um id do BD, sempre criando um Id novo
	
	
	//método create utilizado para demonstrar apenas a lógica de um create em uma API normal, aqui ficaria o codigo para a comm com o BD
	public Person create(Person person) {
		return person;
	}
	
	//Este método serve para demonstrar como funciona a lógica de um update
	public Person update(Person person) {
		return person;
	}
	
	//Este método serve para demonstrar como funciona a lógica de um Delete
		public void delete(String id) {
			
		}
	
	public Person findById(String id) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Bruno");
		person.setLastName("Sabia");
		person.setAddress("SP");
		person.setGender("Male");
		return person ;
	}

	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);			
		}
		
		return persons;
	}


	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name" + i);
		person.setLastName("Last name"+ i);
		person.setAddress("Endereço x"+ i);
		person.setGender("Male");
		return person ;
	}
	
	
}
