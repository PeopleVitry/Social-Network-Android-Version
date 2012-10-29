package com.formation.projetglobal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class NotificationMessage {
	private final static String SERVER_HOST = "talk.google.com";
	private final static int SERVER_PORT = 5222;
	private final static String SERVICE_NAME = "gmail.com";	
	private static ConnectionConfiguration config =new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVICE_NAME);
	private static XMPPConnection m_connection ;
	protected static int MODE_WORLD_READABLE;
	
	
	
	public static void initConnection(final Context context) {
	
		//Initialisation de la connexion(xmpp)
		
        
        m_connection = new XMPPConnection(config);
        try{
        m_connection.connect();
        m_connection.login(MesPreferences.login, MesPreferences.password);
        }
        catch(XMPPException e){
        	e.printStackTrace();
        }
        Presence presence = new Presence(Presence.Type.available);
        m_connection.sendPacket(presence);
       
        //enregistrement de l'écouteur de messages
		PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
		m_connection.addPacketListener(new PacketListener() {
			Message_people msg_recu ;
			private String user_email;
			private String user_guid;
			private String contact_guid;
			private String contact_mail;
				public void processPacket(Packet packet) {
					final Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message.getFrom());
		////////////////recuperer mes données et les données du contacte par la methode de getSharedPreferences(au lieu des intent qui sont pas tres recommandé//
						
						SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
				        user_email = myPrefs.getString("email", "nothing");
				        user_guid = myPrefs.getString("guid", "");
				        
				        SharedPreferences myPrefss = context.getSharedPreferences("myPrefss", MODE_WORLD_READABLE);
				        contact_guid = myPrefss.getString("guidcontact", "nothing");
				        contact_mail = myPrefss.getString("emailcontact", "");
				        
						///////////////envoyer le message recu a la base////////
				        
				        Message_people msg_envoye = new Message_people("",user_guid,contact_guid,message.getBody(),1,"","",user_email,contact_mail);
		                EnvoyerMessage(msg_envoye.getGuid_from(),msg_envoye.getGuid_to(),msg_envoye.getMessage());
							
		                  
							
							//////////////la notification lorsque je recoi un message nimporte ou dans lappli////
						
						
						String ns = Context.NOTIFICATION_SERVICE;
						NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(ns);
						
						int icon = R.drawable.ic_stat_name;
						CharSequence tickerText = "Nouveau message de "+fromName;
						long when = System.currentTimeMillis();

						Notification notification = new Notification(icon, tickerText, when);
						
						CharSequence contentTitle = fromName;
						CharSequence contentText =  message.getBody();
						Intent notificationIntent = new Intent(context, JaberActivity.class);
						//notificationIntent.putExtra("email", fromName);
						//notificationIntent.putExtra("contact_guid", contact_guid);
						PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);


						notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
						
						final int HELLO_ID = 1;

						mNotificationManager.notify(HELLO_ID, notification);
						
						
					
					}
				}
				}, filter);
		}     
	
	
	
//////////requete decriture/////////

 
public static void EnvoyerMessage( String  user_guid , String contact_guid , String message){

    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    nameValuePairs.add(new BasicNameValuePair("guid_from", user_guid));
    nameValuePairs.add(new BasicNameValuePair("guid_to", contact_guid));
    nameValuePairs.add(new BasicNameValuePair("message", message));
    
   	String url = "http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/EcrireMessage" ;
   	
   	Log.i("URL", url);
	try {
	    HttpClient httpclient = new DefaultHttpClient();
	   
  HttpPost request = new HttpPost(url);
	request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	httpclient.execute(request);
  
 


  } catch (Exception e) {
   e.printStackTrace();
 }
		
   	

   	
   }





}





	
