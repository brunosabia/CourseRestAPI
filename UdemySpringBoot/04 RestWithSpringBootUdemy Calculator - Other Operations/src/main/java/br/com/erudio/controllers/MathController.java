package br.com.erudio.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.erudio.mathservices.Operations;

@RestController
public class MathController {

		
		//SOMA
		@RequestMapping(value="/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET)  
		public Double sum(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			Operations op = new Operations(numberOne, numberTwo);
			return op.sum();//retornando a soma	
		}
		
		//SUBTRAÇÃO
		@RequestMapping(value="/sub/{numberOne}/{numberTwo}",method=RequestMethod.GET) 
		public Double sub(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			Operations op = new Operations(numberOne, numberTwo);
			return op.sub();//retornando a subtração
		}
		
		//MULTIPLICAÇÃO
		@RequestMapping(value="/multi/{numberOne}/{numberTwo}",method=RequestMethod.GET)  
		public Double multi(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			Operations op = new Operations(numberOne, numberTwo);
			return op.multi();//retornando a multiplicação
		}
		
		//DIVISÃO
		@RequestMapping(value="/div/{numberOne}/{numberTwo}",method=RequestMethod.GET)  
		public Double div(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			Operations op = new Operations(numberOne, numberTwo);
			return op.div();//retornando a divisão
		}
		
		//MÉDIA
		@RequestMapping(value="/avg/{numberOne}/{numberTwo}",method=RequestMethod.GET)  
		public Double avg(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			Operations op = new Operations(numberOne, numberTwo);
			return op.avg();//retornando a média
			
		}
		
		//RAIZ QUADRADA
		@RequestMapping(value="/sqr/{numberOne}",method=RequestMethod.GET)  
		public Double sqr(@PathVariable("numberOne")String numberOne) throws Exception {

			Operations op = new Operations(numberOne);
			return op.sqr();//retornando a raiz quadrada

		}	
}

//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O