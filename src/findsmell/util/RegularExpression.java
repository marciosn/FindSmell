package findsmell.util;

import java.util.ArrayList;
import java.util.List;

public class RegularExpression {
	
	public String getOnlyName(String name){
		
		List letras = new ArrayList<>();
		for(int i = 0; i < name.length() ; i++){
			char caractere = name.charAt(i);
			if(String.valueOf(caractere).equals("(")){
				return nameIs(letras);
			}	
			letras.add(caractere);
		}
		return nameIs(letras);
	}

	public String nameIs(List nome){
		String palavraInversa = "";
		for(int j = 0 ; j < nome.size(); j++){
			palavraInversa += nome.get(j).toString();
		}
		return palavraInversa;
	}
}
