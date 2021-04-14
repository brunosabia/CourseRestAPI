package br.com.erudio.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person") //nome da tabela
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // significa que o Hibernate vai se responsabilizar pela geração dos Ids
	private Long id;
	
	@Column(name = "first_name", nullable = false, length = 80) //cria uma coluna com o nome first_name que nao pode ser null
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 80)
	private String lastName;
	
	@Column(nullable = false, length = 100)	//cria uma coluna com o mesmo nome da variável
	private String address;
	
	@Column(nullable = false, length = 6) //limita a quantidade de caractéres que podem ser add
	private String gender;
	
	@Column(nullable = false) //limita a quantidade de caractéres que podem ser add
	private Boolean enabled;
	
	public Person() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


}
