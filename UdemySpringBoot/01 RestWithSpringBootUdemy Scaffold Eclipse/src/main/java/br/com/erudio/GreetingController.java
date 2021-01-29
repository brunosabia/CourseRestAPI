package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//para o rest reconhecer a classe como um controller é necessário colocar o seguinte código:
@RestController
public class GreetingController {

		private static final String template = "Hello, %s!";
		private final AtomicLong counter = new AtomicLong();
		
		//Para o rest reconhecer o método como um Endpoint, colocar o seguinte código
		@RequestMapping("/greeting")
		public Greeting greeting(@RequestParam(value="name", defaultValue = "World")String name) {
			return new Greeting(counter.incrementAndGet(),String.format(template, name));
			
		}
		
}
