package br.com.erudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.erudio.security.jwt.JwtConfigurer;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	//A Encryptação da minha senha utilizará desse Algoritmo BCryptPasswordEncoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

	//método que especificamente configura a aplicação:
	protected void configure(HttpSecurity http) throws Exception{
		http
		.httpBasic().disable() //para desabilitar a authentication basic
		.csrf().disable() //desabilita o suporte csrf , que é ligado por default (Cross-Site Request Forgery)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //não irá guardar os estados
		.and()
			.authorizeRequests() // para autorizar requisições
			.antMatchers("/auth/signin","/api-docs/**", "swagger-ui.html**").permitAll() //vamos passar os padrões que serão permitidos
			.antMatchers("/api/**").authenticated() //pra chegar aqui precisa estar authenticated
			.antMatchers("/users").denyAll() //ngm pode acessar
		.and()
		.apply(new JwtConfigurer(tokenProvider)); //aplicar a configurações
	}
}
