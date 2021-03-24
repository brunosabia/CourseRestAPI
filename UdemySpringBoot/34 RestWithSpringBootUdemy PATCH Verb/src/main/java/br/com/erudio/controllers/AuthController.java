package br.com.erudio.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.repository.UserRepository;
import br.com.erudio.security.AccountCredentialsVO;
import br.com.erudio.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository repository;
	
	//Sign In Method
	@ApiOperation(value = "Authenticates user and returns token")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin",produces = {"application/json", "application/xml","application/x-yaml" },
			 consumes = {"application/json", "application/xml","application/x-yaml" }) 
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data){ //recebe o VO como param
		try {
			
				var username = data.getUsername(); //armazena Username e password do VO 
				var password = data.getPassword();
			
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); //realiza a authenticação
			
				var user = repository.findByUsername(username); //busca o user no repository
			
				var token = "";
			
				//se o user for encontrado no repositório:
				if(user != null) {
					token = tokenProvider.createToken(username, user.getRoles()); //cria o token do user com as permissoes dele 
					} else {
						throw new UsernameNotFoundException("Username "+ username + " not found."); // trata a exceção
					}
			
				
					Map<Object, Object> model = new HashMap<>(); //Montando um obj para ser retornado
					model.put("username", username); //assimilando o username
					model.put("token", token); //assimilando o token
					return ok(model); //retorna o model de retorno
			
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied.",e);
		}
	}
}


//http://localhost:8080/auth/signin


/*
{
    "username":"leandro",
    "password":"admin123"
}
*/
