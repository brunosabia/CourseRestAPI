package br.com.erudio.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean{

	//Dependency Injection
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
	//Constructor
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}


	//método sobrescrito da GenericFilterBean
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
				// Filtra todo o tráfego HTTP 
				// Obtém o token do cabeçalho da request
		String token = tokenProvider.resolveToken((HttpServletRequest) request); //token que vem no cabeçalho da request e executará o substring
		
		if(token != null && tokenProvider.validateToken(token)) { //se o token for valido
			Authentication auth = tokenProvider.getAuthentication(token); // se o token for valido nós buscaremos as autorizações dele
			if(auth != null) { //se as autorizações forem validas
				SecurityContextHolder.getContext().setAuthentication(auth); //vamos setar a autenticação ao context
			}
		}
		// Aplica o filtro
		chain.doFilter(request, response);
	}

}
