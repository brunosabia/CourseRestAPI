package br.com.erudio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		//esse query nao precisa de @Modifying pois é só de leitura, nao manipulação
		@Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT ('%', :firstName, '%'))") // o "LIKE" só é recomendado para aplicações pequenas, pois ele busca em todas as strings de todo o repository resultando em queda de performance
		//além disso, os '%' antes e depois significam que se tiver o nome desejado no meio ou no fim da palavra ele sera retornado da mesma forma, por exemplo
		//vc busca o nome "ANA", ele irá retornar mariANA também. (se remover o primeiro ele busca somente os que começam com ANA e se tirar o do fim ele busca os que terminam com ANA
		//Query em JPQL - NÃO É QUERY MYSQL, para passar queries de SQL é preciso de outros métodos
		Page<Person> findPersonByName(@Param("firstName") String firstName, Pageable pageable); //é um
		
		//o spring data consegue fazer a busca a partir de apenas isso
	
}
