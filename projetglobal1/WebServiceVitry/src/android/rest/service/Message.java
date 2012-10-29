package android.rest.service;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Message {
	
   private 	String Pseudo;
   private 	String Msg;
   private int prive;
   private String recepteur ;
   
   
public Message(String pseudo, String msg) {
	super();
	this.Pseudo = pseudo;
	this.Msg = msg;
}



  public Message(String pseudo, String msg, int prive, String recepteur) {
	super();
	this.Pseudo = pseudo;
	this.Msg = msg;
	this.prive = prive;
	this.recepteur = recepteur;
}



public int getPrive() {
	return prive;
}


public void setPrive(int prive) {
	this.prive = prive;
}


public String getRecepteur() {
	return recepteur;
}


public void setRecepteur(String recepteur) {
	this.recepteur = recepteur;
}


public  Message(){
	
	
  }
public String getPseudo() {
	return Pseudo;
}
public void setPseudo(String pseudo) {
	Pseudo = pseudo;
}
public String getMsg() {
	return Msg;
}
public void setMsg(String msg) {
	Msg = msg;
}
@Override
public String toString() {
	return "Message [Pseudo=" + Pseudo + ", Msg=" + Msg + ", prive="
			+ prive + ", recepteur=" + recepteur + "]";
}

}
