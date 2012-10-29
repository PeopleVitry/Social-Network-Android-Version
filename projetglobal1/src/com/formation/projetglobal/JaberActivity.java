package com.formation.projetglobal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

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
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class JaberActivity extends Activity {
	
	private final static String SERVER_HOST = "talk.google.com";
	private final static int SERVER_PORT = 5222;
	private final static String SERVICE_NAME = "gmail.com";	
	private  static String contact_mail;
	private ListView list;
	private List<Message_people> m_discussionThread;
	private List<Message_people> m_discussionThreadws;
	private ArrayAdapter<Message_people> m_discussionThreadAdapter;
	private ArrayAdapter<Message_people> m_discussionThreadAdapterws;
	private XMPPConnection m_connection;
	private Handler m_handler;
	private String user_email;
	private String user_guid;
	private String contact_guid;
	private Message_people msg_recu ;
	private Intent thisIntent ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        ///récuperation du mail du contacte et de son guid////
        
        
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        user_email = myPrefs.getString("email", "nothing");
        user_guid = myPrefs.getString("guid", "");
        
        SharedPreferences myPrefss = this.getSharedPreferences("myPrefss", MODE_WORLD_READABLE);
        contact_guid = myPrefss.getString("guidcontact", "nothing");
        contact_mail = myPrefss.getString("emailcontact", "");
        
       
        thisIntent = getIntent();
        
        m_handler = new Handler();
		try {
			initConnection();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		//Intent thisIntent = getIntent();
		
		 
		final EditText message = (EditText) this.findViewById(R.id.sendText);		
		 list = (ListView) this.findViewById(R.id.listMessages);
		
		/////récupéere les ancienne conversation////
	 
		
		m_discussionThreadws = new ArrayList<Message_people>();
		m_discussionThreadAdapterws = new ArrayAdapter<Message_people>(this,R.layout.multi_line_list_item, m_discussionThreadws);
		list.setAdapter(m_discussionThreadAdapterws);
		
		 m_discussionThreadws.addAll(getMessage(requetServeur(user_guid, contact_guid)));
		 m_discussionThreadAdapterws.notifyDataSetChanged();
		
		
		Button send = (Button) this.findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				try{
				String to = contact_mail;
				String text = message.getText().toString();
				
		        //////////ecrire dans la base////
				Message_people msg_envoye = new Message_people("",user_guid,contact_guid,text,1,"","",user_email,contact_mail);
                EnvoyerMessage(msg_envoye.getGuid_from(),msg_envoye.getGuid_to(),msg_envoye.getMessage());
		        ///////////////////////////////////////////
                
				Message msg = new Message(to, Message.Type.chat);
				
				msg.setBody(text);
							
				m_connection.sendPacket(msg);
				m_discussionThreadws.add(msg_envoye);
				m_discussionThreadAdapterws.notifyDataSetChanged();
			}
				catch(Exception e){
					System.out.println(e.getMessage());			
					}
				}
		});
	}


	public void initConnection() throws XMPPException {
		//Initialisation de la connexion

        ConnectionConfiguration config =
                new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVICE_NAME);
        m_connection = new XMPPConnection(config);
        m_connection.connect();
        m_connection.login(MesPreferences.login, MesPreferences.password);
        Presence presence = new Presence(Presence.Type.available);
        m_connection.sendPacket(presence);
       
        //enregistrement de l'écouteur de messages
		PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
		m_connection.addPacketListener(new PacketListener() {
			Message_people msg_recu ;
				public void processPacket(Packet packet) {
					final Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						if(fromName.equals(contact_mail)){
							
						/////construire notre constructeur qui contiendra le message,mailcontact et mailutilis///
						msg_recu = new Message_people(message.getBody(), contact_mail , user_email);
					////////////////////////////////////////////////////////////////////////////////////////////
						///ajouter le message a la liste des messages//////
						m_discussionThreadws.add(msg_recu);
						/////////////////////////////////////////////////
						
						 ((Vibrator) getSystemService(Map.VIBRATOR_SERVICE)).vibrate(1000);
						
						Log.i("Message recu", msg_recu.getMessage());
						
						}
						
						m_handler.post(new Runnable() {
							public void run() {
								m_discussionThreadAdapterws.notifyDataSetChanged();
								
							}
						});
					}
				}
			}, filter);
	}
	
	/////requete qui nous renvoi les ancienne conversation////
	
public String requetServeur( String  user_guid , String contact_guid){
    	
    	String jaxrsmessage="offfffffffffffffff";


	try {
	    HttpClient httpclient = new DefaultHttpClient();

	    String url = "http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"" ;
	    Log.i("URL", url);
   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"");
   
    HttpResponse response = httpclient.execute(request);
    HttpEntity entity = response.getEntity();
    InputStream instream = entity.getContent();
   jaxrsmessage = read(instream);
  


   } catch (ClientProtocolException e) {
    e.printStackTrace();
     } catch (IOException e) {
    e.printStackTrace();
  }
	Log.i("request Server :", jaxrsmessage);
	return jaxrsmessage;	
    	

    	
    }

//////////////////methode read classic////////////////

private static String read(InputStream instream) {
    StringBuilder sb = null;
    try {
         sb = new StringBuilder();
         BufferedReader r = new BufferedReader(new InputStreamReader(
                   instream));
    for (String line = r.readLine(); line != null; line = r.readLine()) {
         	sb.append(line);
		}

		instream.close();

    } catch (IOException e) {
    }
    return sb.toString();

}
	
	
	



/////////////methode getmessage///////

public List<Message_people>  getMessage(String  jsonString){
	Log.i("SERVER", jsonString);
    
   List<Message_people> msgList= new ArrayList<Message_people>();
	
	
	try {
		
		JSONObject json = new JSONObject(jsonString);
		
		JSONArray jArray = json.getJSONArray("messagePeople");
		
	for (int i = 0; i < jArray.length(); i++) {
			
			JSONObject jsonObject = jArray.getJSONObject(i);	
		
			Message_people msg= new Message_people(jsonObject.getString("message"),jsonObject.getString("email_from"),jsonObject.getString("email_to"));
			msgList.add(msg);
			
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return msgList;
    	
	 	   	
    }

////////////////////envoyer un message///////////////////////

public void EnvoyerMessage( String  user_guid , String contact_guid , String message){

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
   
   /////////////////mettre le message comme lu (nn utlisé) ///////////////////



public String messagelu( String  contact_guid , String user_guid){
	
	String jaxrsmessage="offfffffffffffffff";


try {
    HttpClient httpclient = new DefaultHttpClient();
//HttpGet request = new HttpGet("http://10.10.120.239:8080/AndroidJAX-RS/allo");
    String url = "http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"" ;
    Log.i("URL", url);
HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"");

//request.addHeader("Accept", "text/html");
//request.addHeader("Accept", "text/xml");
//request.addHeader("Accept", "text/plain");      
HttpResponse response = httpclient.execute(request);
HttpEntity entity = response.getEntity();
InputStream instream = entity.getContent();
jaxrsmessage = read(instream);



} catch (ClientProtocolException e) {
e.printStackTrace();
 } catch (IOException e) {
e.printStackTrace();
}
Log.i("request Server :", jaxrsmessage);
return jaxrsmessage;	
	

	
}


public static String requetServeur( String  email){
	
	String jaxrsmessage="offfffffffffffffff";


try {
    HttpClient httpclient = new DefaultHttpClient();

    String url = "http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/messagerie/email-"+email ;
    Log.i("URL", url);
HttpGet request = new HttpGet(url);
    
HttpResponse response = httpclient.execute(request);
HttpEntity entity = response.getEntity();
InputStream instream = entity.getContent();
jaxrsmessage = read(instream);



} catch (ClientProtocolException e) {
e.printStackTrace();
 } catch (IOException e) {
e.printStackTrace();
}
Log.i("request Server :", jaxrsmessage);
return  jaxrsmessage;	
	

	
}

   
   
}