/**
 * 
 */
package com.formation.projetglobal;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author unknown
 *
 */

@XmlRootElement

public class Message_people_received {
	private  String guid_from ;
	private int connecte ;
	private String name ;
	private String username ;
	private int countNewMessage ;
	
	
	
	/**
	 * @param guid_from
	 * @param connecte
	 * @param name
	 * @param username
	 * @param countNewMessage
	 */
	public Message_people_received(String guid_from, int connecte, String name,
			String username, int countNewMessage) {
		super();
		this.guid_from = guid_from;
		this.connecte = connecte;
		this.name = name;
		this.username = username;
		this.countNewMessage = countNewMessage;
	}
	/**
	 * @return the guid_from
	 */
	public String getGuid_from() {
		return guid_from;
	}
	/**
	 * @param guid_from the guid_from to set
	 */
	public void setGuid_from(String guid_from) {
		this.guid_from = guid_from;
	}
	/**
	 * @return the connecte
	 */
	public int getConnecte() {
		return connecte;
	}
	/**
	 * @param connecte the connecte to set
	 */
	public void setConnecte(int connecte) {
		this.connecte = connecte;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the countNewMessage
	 */
	public int getCountNewMessage() {
		return countNewMessage;
	}
	/**
	 * @param countNewMessage the countNewMessage to set
	 */
	public void setCountNewMessage(int countNewMessage) {
		this.countNewMessage = countNewMessage;
	}
	
}
