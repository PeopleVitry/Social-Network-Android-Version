/**
 * 
 */
package com.formation.projetglobal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListMessageActivity extends Activity {

    private ArrayList<String> messages = new ArrayList();
    private Handler mHandler = new Handler();
    private SettingsDialog mDialog;
    private String mRecipient;
    private EditText mSendText;
    private ListView mList;
    private XMPPConnection connection;
    
    private String contact_guid ;
    private String contact_email ;
    private String user_email  ;
    private String user_guid ;
    private List <Message_people> msg = new ArrayList<Message_people>() ;
    
    
    private   Handler handler = new Handler() {

    	//private int id_utilisateur;

		public void handleMessage(android.os.Message msg) {

    	     if(msg.what == 0) {
    	    	setListAdapter();
    	     }
 
    	 };

    };
    
    
    

    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.listmessage);
        mSendText = (EditText) this.findViewById(R.id.sendText1);
        mList = (ListView) this.findViewById(R.id.listMessages1);
        
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        user_email = myPrefs.getString("email", "nothing");
        user_guid = myPrefs.getString("guid", "");
        Intent thisIntent = getIntent();
        contact_guid = thisIntent.getExtras().getString("guid")+"";
        contact_email = thisIntent.getExtras().getString("email");
        
        
        
        
         msg = getMessage(requetServeur(user_guid, contact_guid));
         setListAdapter();
        
        

        // Set a listener to send a chat text message
        Button send = (Button) this.findViewById(R.id.send1);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //String to = mRecipient.getText().toString();
                String text = mSendText.getText().toString();
                mSendText.setText("");
                Message_people msg_envoye = new Message_people("",user_guid,contact_guid,text,0);
                EnvoyerMessage(msg_envoye.getGuid_from(),msg_envoye.getGuid_to(),msg_envoye.getMessage());
                msg.add(msg_envoye);
                mSendText.setText("");
                //Toast.makeText(ListMessageActivity.this, text, Toast.LENGTH_LONG).show();
                setListAdapter();
            }
        });
        
        Thread messageUpdate = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try{
						Thread.sleep(5000);
						msg.addAll(getMessage(UpdateMsg(user_guid, contact_guid)));
						handler.sendEmptyMessage(0);
						}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}
		});
        
        messageUpdate.start();
        
    }

    /**
     * Called by Settings dialog when a connection is establised with the XMPP server
     *
     * @param connection
     */
  /*  public void setConnection
            (XMPPConnection
                    connection) {
        this.connection = connection;
        if (connection != null) {
            // Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() {
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    if (message.getBody() != null) {
                        String fromName = StringUtils.parseBareAddress(message.getFrom());
                        Log.i("XMPPClient", "Got text [" + message.getBody() + "] from [" + fromName + "]");
                        messages.add(fromName + ":");
                        messages.add(message.getBody());
                        // Add the incoming message to the list view
                        mHandler.post(new Runnable() {
                            public void run() {
                                setListAdapter();
                            }
                        });
                    }
                }
            }, filter);
        }
    }*/

    private void setListAdapter
            () {
        ArrayAdapter<Message_people> adapter = new ArrayAdapter<Message_people>(this,
                R.layout.multi_line_list_item,
                msg);
        mList.setAdapter(adapter);
    }
    
    
public String requetServeur( String  user_guid , String contact_guid){
    	
    	String jaxrsmessage="offfffffffffffffff";


	try {
	    HttpClient httpclient = new DefaultHttpClient();
//    HttpGet request = new HttpGet("http://10.10.120.239:8080/AndroidJAX-RS/allo");
	    String url = "http://"+MesPreferences.adress_ip+":8080/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"" ;
	    Log.i("URL", url);
   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":8080/WebServiceVitry/messagerie/guid_1-"+user_guid+"-guid_2-"+contact_guid+"");
   
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
   
   public List<Message_people>  getMessage(String  jsonString){
    	Log.i("SERVER", jsonString);
	    
	   List<Message_people> msgList= new ArrayList<Message_people>();
   	
   	
   	try {
   		
   		JSONObject json = new JSONObject(jsonString);
   		
   		JSONArray jArray = json.getJSONArray("messagePeople");
   		
   	for (int i = 0; i < jArray.length(); i++) {
				
				JSONObject jsonObject = jArray.getJSONObject(i);	
			//	Ami monami=new Ami(jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getString("email"));
				Message_people msg= new Message_people(jsonObject.getString("id"), jsonObject.getString("guid_from"), jsonObject.getString("guid_to"), jsonObject.getString("message"), jsonObject.getInt("lu"));
				msgList.add(msg);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
   	
   	
		return msgList;
	    	
		 	   	
	    }
    
   
   // cette fonction envoi les message au service web
   
   public void EnvoyerMessage( String  user_guid , String contact_guid , String message){
   	
   	String url = "http://"+MesPreferences.adress_ip+":8080/WebServiceVitry/messagerie/guid_from-"+user_guid+"-guid_to-"+contact_guid+"" ;
   	Log.i("URL", url);
	try {
	    HttpClient httpclient = new DefaultHttpClient();
//   HttpGet request = new HttpGet("http://10.10.120.239:8080/AndroidJAX-RS/allo");
  HttpGet request = new HttpGet(url);
	//request.addHeader("Accept", "text/html");
	//request.addHeader("Accept", "text/xml");
	//request.addHeader("Accept", "text/plain");      
   HttpResponse response = httpclient.execute(request);
   /* HttpEntity entity = response.getEntity();
   InputStream instream = entity.getContent();
  jaxrsmessage = read(instream);*/
 


  } catch (Exception e) {
   e.printStackTrace();
 }
	//return jaxrsmessage;	
   	

   	
   }
   
   public boolean isMsgSend(String jsonString){
	   
	   return true;
	   
   }
   
   
   public String UpdateMsg( String  user_guid , String contact_guid){
   	
   	String jaxrsmessage="offfffffffffffffff";


	try {
	    HttpClient httpclient = new DefaultHttpClient();
//   HttpGet request = new HttpGet("http://10.10.120.239:8080/AndroidJAX-RS/allo");
	    String url = "http://"+MesPreferences.adress_ip+":8080/WebServiceVitry/messagerie/guid_from-"+contact_guid+"-guid_to-"+user_guid+"-nouveau-21" ;
	    Log.i("URL", url);
  HttpGet request = new HttpGet(url);
  
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
	//Log.i("request Server :", jaxrsmessage);
	return jaxrsmessage;	
   	

   	
   }
   
   
   
   
}

