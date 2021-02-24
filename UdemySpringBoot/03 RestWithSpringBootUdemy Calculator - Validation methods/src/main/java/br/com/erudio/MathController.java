package br.com.erudio;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.Exception.UnsupportedMathOperationException;

//para o rest reconhecer a classe como um controller é necessário colocar o seguinte código:
@RestController
public class MathController {

		
		//Para o rest reconhecer o método como um Endpoint, colocar o seguinte código
		@RequestMapping(value="/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET)  //para definir que numberone e numberTwo são params eles precisam estar entre chaves
		public Double sum(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
			
			if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {//precisamos garantir que o método retornara um valor numérico
				
				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
			return sum;//retornando a soma
			
		}
		

		private Double convertToDouble(String strNumber) {
			
			if(strNumber == null) return 0D;
			String number = strNumber.replaceAll("," , ".");
			if(isNumeric(number)) {
				return Double.parseDouble(number);
			}
			return 0D;
		}


		private boolean isNumeric(String strNumber) {
			// TODO Auto-generated method stub
			if(strNumber == null) return false;
			String number = strNumber.replaceAll("," , ".");
			return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //esse código estranho é chamado de regex, uma comparação que retorna true ou false se ele matches com um numero.
			
		}
		
}

//vamos REFATORAR o nome dessa classe, selecione o nome da classe "GreetingController" e aperte alt+shift+R para então renomear a classe e se optar, arquivo, métodos... etc.

//Para organizar as importações  pressione Ctrl + Shift + O