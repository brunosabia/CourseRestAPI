package br.com.erudio.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.erudio.Exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret}") //settando valores default
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-length:3600000}")//settando valores default
	private long validityInMilliseconds = 3600000; //1hr (o long precisa ser do tipo primitivo(minúsculo))

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	//esse método é responsável por encodar a nossa Secret key logo que essa dependencia for chamada.
	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	//Criando o token
	public String createToken(String username, List<String> roles) {
		Claims claims =Jwts.claims().setSubject(username); //claims tem um significado similar a "direitos", "autorizações"
		claims.put("roles", roles); //definindo os papéis do user
		
		Date now = new Date(); //pegando uma variavel do horario atual
		Date validity = new Date(now.getTime()+ validityInMilliseconds); //somando a 1hr ao horario atual para calcular o tempo de duração do token
		
		return Jwts.builder()
				.setClaims(claims) //setar as autorizações
				.setIssuedAt(now) //quando o token é criado
				.setExpiration(validity) //validade do token
				.signWith(SignatureAlgorithm.HS256, secretKey) //passar o algoritmo usado para encriptar e a palavra secreta
				.compact(); //compactando
	}
	
	private String getUsername(String token) {
		
		return Jwts.parser() //pega o token
				.setSigningKey(secretKey) //seta a secretKey
				.parseClaimsJws(token) //abre a estrutura do token
				.getBody() //vai no conteudo do token (no Payload)
				.getSubject(); //pega o subject ( usuário) do token
	}
	
	//vc recebe o token como param e esse método busca o username desse token e retorna o username e os seus roles(autorizações)
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	
	
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			//normalmente um bearer token vem assim:
			//Bearer KN123h8bNkInKnGkiyIYjGhIjlKPOKlK123Jk923NK3J4 
			//vamos remover o "Bearer " e deixar apenas o token:
			return bearerToken.substring(7,bearerToken.length());
		}
		
		return null;
	}
	
	//método que abre o token e valida se ele expirou ou não
	public boolean validateToken(String token) 
	{
		try {
						Jws<Claims> claims = Jwts.parser() //pega o token
						.setSigningKey(secretKey) //seta a secretKey
						.parseClaimsJws(token); //abre a estrutura do token
			
				if(claims.getBody().getExpiration().before(new Date())) //se a expiration date é before a data atual = o token expirou
				{
					return false;
				}
			
				return true;
			}catch(Exception e)
			{
				throw new InvalidJwtAuthenticationException("Expired or invalid token");
			}	
	}
	
}
