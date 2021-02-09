package br.com.erudio.mathservices;

import org.springframework.stereotype.Service;

@Service
public class ValidationMethods {

	
	//Support methods
			public  Double convertToDouble(String strNumber) {
				
				if(strNumber == null) return 0D;
				String number = strNumber.replaceAll("," , ".");
				if(isNumeric(number)) {
					return Double.parseDouble(number);
				}
				return 0D;
			}


			public boolean isNumeric(String strNumber) {
				// TODO Auto-generated method stub
				if(strNumber == null) return false;
				String number = strNumber.replaceAll("," , ".");
				return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //esse código estranho é chamado de regex, uma comparação que retorna true ou false se ele matches com um numero.
				
			}
}
