package br.com.erudio.mathservices;

import br.com.erudio.Exception.UnsupportedMathOperationException;

public class Operations {

	//attributes
	public String numberOne;
	public String numberTwo;

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
		if(!ValidationMethods.isNumeric(numberOne) || !ValidationMethods.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double sum = ValidationMethods.convertToDouble(numberOne) + ValidationMethods.convertToDouble(numberTwo);
		return sum;
	}

	//SUBTRAÇÃO
	public Double sub() throws Exception {
		if(!ValidationMethods.isNumeric(numberOne) || !ValidationMethods.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double sub = ValidationMethods.convertToDouble(numberOne) - ValidationMethods.convertToDouble(numberTwo);
		return sub;
	}

	//MULTIPLICAÇÃO
	public Double multi() throws Exception {
		if(!ValidationMethods.isNumeric(numberOne) || !ValidationMethods.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double multi = ValidationMethods.convertToDouble(numberOne) * ValidationMethods.convertToDouble(numberTwo);
		return multi;
	}
	//DIVISÃO
	public Double div() throws Exception {
		if(!ValidationMethods.isNumeric(numberOne) || !ValidationMethods.isNumeric(numberTwo)) {

			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double div = ValidationMethods.convertToDouble(numberOne) / ValidationMethods.convertToDouble(numberTwo);
		return div;
	}
	//MÉDIA
		public Double avg() throws Exception {
			if(!ValidationMethods.isNumeric(numberOne) || !ValidationMethods.isNumeric(numberTwo)) {

				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			Double avg = (ValidationMethods.convertToDouble(numberOne) + ValidationMethods.convertToDouble(numberTwo))/2;
			return avg;
		}
		//RAIZ QUADRADA
		public Double sqr() throws Exception {
			if(!ValidationMethods.isNumeric(numberOne)) {

				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			Double doub = (ValidationMethods.convertToDouble(numberOne));
			Double sqr = Math.sqrt(doub);
			return sqr;
		}


}
