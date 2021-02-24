package br.com.erudio;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//para o rest reconhecer a classe como um controller é necessário colocar o seguinte código:
@RestController
public class MathController {

		
		//Para o rest reconhecer o método como um Endpoint, colocar o seguinte código
		@RequestMapping(value="/sum/numberOne/numberTwo",method=RequestMethod.GET)
		public Double sum(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {//precisamos garantir que o método retornara um valor numérico
				
				throw new Exception();
			}
			Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
			return sum;//retornando apenas um double qualquer
			
		}
		

		private Double convertToDouble(String numberOne) {
			// TODO Auto-generated method stub
			return 1D;
		}


		private boolean isNumeric(String number) {
			// TODO Auto-generated method stub
			return false;
		}
		
}

//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O