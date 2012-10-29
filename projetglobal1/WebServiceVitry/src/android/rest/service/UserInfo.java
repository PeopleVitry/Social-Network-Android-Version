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

public class UserInfo {
	private String guid ;
	private int connecte ;
	private String name ;
	private String username ;
	private String email ;
	private double latitude ;
	private double longitude ;
	private boolean succes ;
	
	
	
	
	
	/**
	 * 
	 */
	public UserInfo() {
		super();
	}
	/**
	 * @return the succes
	 */
	public boolean isSucces() {
		return succes;
	}
	/**
	 * @param succes the succes to set
	 */
	public void setSucces(boolean succes) {
		this.succes = succes;
	}
	/**
	 * @param guid
	 * @param connecte
	 * @param name
	 * @param username
	 * @param email
	 * @param latitude
	 * @param longitude
	 */
	public UserInfo(String guid, int connecte, String name, String username,
			String email, double latitude, double longitude) {
		super();
		this.guid = guid;
		this.connecte = connecte;
		this.name = name;
		this.username = username;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
