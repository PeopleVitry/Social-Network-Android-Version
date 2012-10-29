package android.rest.service;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ClassObjet {
	

	private int numero;
	private String type_calss;
	private int objet_code;
	
	
	public ClassObjet(int numero, String type_calss, int objet_code) {
	
		this.numero = numero;
		this.type_calss = type_calss;
		this.objet_code = objet_code;
	}
	
	
	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getType_calss() {
		return type_calss;
	}


	public void setType_calss(String type_calss) {
		this.type_calss = type_calss;
	}


	public int getObjet_code() {
		return objet_code;
	}


	public void setObjet_code(int objet_code) {
		this.objet_code = objet_code;
	}


	public ClassObjet(){
		
	}

}
