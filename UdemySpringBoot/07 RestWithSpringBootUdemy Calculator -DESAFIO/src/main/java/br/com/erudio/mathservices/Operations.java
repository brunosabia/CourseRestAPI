package br.com.erudio.mathservices;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.erudio.Exception.UnsupportedMathOperationException;

public class Operations {

	//attributes
	public String numberOne;
	public String numberTwo;
	
	@Autowired //O desafio era remover os métodos static e utilizar o @Service e @Autowired para realizar as injecoes de dependencia atravez do springBoot
	private ValidationMethods vm;

	//Constructors
	public Operations(String numberOne, String numberTwo) {
		super();
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
	}

	public Operations(String numberOne) {
		super();
		this.numberOne = numberOne;
	}

	//Operations:

	//SOMA
	public Double sum() throws Exception {
		if(!vm.isNumeric(numberOne) || !vm.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double sum = vm.convertToDouble(numberOne) + vm.convertToDouble(numberTwo);
		return sum;
	}

	//SUBTRAÇÃO
	public Double sub() throws Exception {
		if(!vm.isNumeric(numberOne) || !vm.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double sub = vm.convertToDouble(numberOne) - vm.convertToDouble(numberTwo);
		return sub;
	}

	//MULTIPLICAÇÃO
	public Double multi() throws Exception {
		if(!vm.isNumeric(numberOne) || !vm.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double multi = vm.convertToDouble(numberOne) * vm.convertToDouble(numberTwo);
		return multi;
	}
	//DIVISÃO
	public Double div() throws Exception {
		if(!vm.isNumeric(numberOne) || !vm.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double div = vm.convertToDouble(numberOne) / vm.convertToDouble(numberTwo);
		return div;
	}
	//MÉDIA
		public Double avg() throws Exception {
			if(!vm.isNumeric(numberOne) || !vm.isNumeric(numberTwo)) {

				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			Double avg = (vm.convertToDouble(numberOne) + vm.convertToDouble(numberTwo))/2;
			return avg;
		}
		//RAIZ QUADRADA
		public Double sqr() throws Exception {
			if(!vm.isNumeric(numberOne)) {

				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			Double doub = (vm.convertToDouble(numberOne));
			Double sqr = Math.sqrt(doub);
			return sqr;
		}


}
