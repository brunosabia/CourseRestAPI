package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration //essa annotation permite que o applicationContext do Spring seja automaticamente carregado baseado nos Jars e nas configs que vc define
//ela sempre é feita depois que os bins ja foram registrados no applicationContext. Te dá uma vantagem por reduzir a quantidade de configs que vc tem que se preocupar
@ComponentScan
//essa anotação é usada para dizer para o spring boot que ele deve scannear os nossos packages e encontrar os arquivos de config. como o WebConfig.java

public class Startup {
	
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		/*
		 * Método que será feito para encryptar uma senha recebida e no caso, retornar essa senha: 
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("senha123");
		System.out.println("My hash " + result);
		*/
	}
}
