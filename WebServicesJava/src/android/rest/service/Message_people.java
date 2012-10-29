/**
 * 
 */
package android.rest.service;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author unknown
 *
 */

@XmlRootElement

public class Message_people {
	private String id ;
	private String guid_from ;
	private String guid_to ;
	private String message ;
	private int lu ;
	private String lu_a ;
	private String envoye_a ;
	private String email_from ;
	private String email_to ;
	
	/**
	 * 
	 */
	public Message_people() {
		super();
	}


	/**
	 * @param id
	 * @param guid_from
	 * @param guid_to
	 * @param message
	 * @param lu
	 * @param lu_a
	 * @param envoye_a
	 * @param email_from
	 * @param email_to
	 */
	public Message_people(String id, String guid_from, String guid_to,
			String message, int lu, String lu_a, String envoye_a,
			String email_from, String email_to) {
		super();
		this.id = id;
		this.guid_from = guid_from;
		this.guid_to = guid_to;
		this.message = message;
		this.lu = lu;
		this.lu_a = lu_a;
		this.envoye_a = envoye_a;
		this.email_from = email_from;
		this.email_to = email_to;
	}


	/**
	 * @param id
	 * @param guid_from
	 * @param guid_to
	 * @param message
	 * @param lu
	 * @param lu_a
	 * @param envoye_a
	 */
	
	public Message_people(String id, String guid_from, String guid_to,
			String message, int lu, String lu_a, String envoye_a) {
		super();
		this.id = id;
		this.guid_from = guid_from;
		this.guid_to = guid_to;
		this.message = message;
		this.lu = lu;
		this.lu_a = lu_a;
		this.envoye_a = envoye_a;
	}
	
	
	/**
	 * @param message
	 * @param email_from
	 * @param email_to
	 */
	public Message_people(String message, String email_from, String email_to) {
		super();
		this.message = message;
		this.email_from = email_from;
		this.email_to = email_to;
	}


	/**
	 * @param id
	 * @param guid_from
	 * @param guid_to
	 * @param message
	 * @param lu
	 */
	public Message_people(String id, String guid_from, String guid_to,
			String message, int lu) {
		super();
		this.id = id;
		this.guid_from = guid_from;
		this.guid_to = guid_to;
		this.message = message;
		this.lu = lu;
	}


	

	/**
	 * @param guid_from
	 * @param guid_to
	 */
	public Message_people(String guid_from, String guid_to) {
		super();
		this.guid_from = guid_from;
		this.guid_to = guid_to;
	}

	
	/**
	 * @return the email_from
	 */
	public String getEmail_from() {
		return email_from;
	}


	/**
	 * @param email_from the email_from to set
	 */
	public void setEmail_from(String email_from) {
		this.email_from = email_from;
	}


	/**
	 * @return the email_to
	 */
	public String getEmail_to() {
		return email_to;
	}


	/**
	 * @param email_to the email_to to set
	 */
	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the guid_to
	 */
	public String getGuid_to() {
		return guid_to;
	}
	/**
	 * @param guid_to the guid_to to set
	 */
	public void setGuid_to(String guid_to) {
		this.guid_to = guid_to;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the lu
	 */
	public int getLu() {
		return lu;
	}
	/**
	 * @param lu the lu to set
	 */
	public void setLu(int lu) {
		this.lu = lu;
	}
	/**
	 * @return the lu_a
	 */
	public String getLu_a() {
		return lu_a;
	}
	/**
	 * @param lu_a the lu_a to set
	 */
	public void setLu_a(String lu_a) {
		this.lu_a = lu_a;
	}
	/**
	 * @return the envoye_a
	 */
	public String getEnvoye_a() {
		return envoye_a;
	}
	/**
	 * @param envoye_a the envoye_a to set
	 */
	public void setEnvoye_a(String envoye_a) {
		this.envoye_a = envoye_a;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [guid_from=" + guid_from + ", guid_to="
				+ guid_to + "\n message=" + message + "]";
	}
	
}
