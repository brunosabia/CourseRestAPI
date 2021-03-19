package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//Vamos usar o JPQL (Jakarta Persistence Query Language , former Java Persistence Query Language) para mostrar outra maneira de buscar no repositório.
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName") //JPQL
	User findByUsername(@Param("userName") String userName);
	//o Query ali irá selecionar o User com o userName recebido pelo findByUsername();
	//o spring data consegue fazer a busca a partir de apenas isso
}
