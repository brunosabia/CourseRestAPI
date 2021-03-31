package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	//Vamos usar o JPQL (Jakarta Persistence Query Language , former Java Persistence Query Language) para mostrar outra maneira de buscar no repositório.
		//método que ira modificar apenas o "enabled" da Person
	
		@Modifying//essa annotation significa que essa QUERY abaixo irá modificar/manipular os dados
		@Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id") //JPQL
		void disablePerson(@Param("id") Long id);
		
		
		//o spring data consegue fazer a busca a partir de apenas isso
	
}
