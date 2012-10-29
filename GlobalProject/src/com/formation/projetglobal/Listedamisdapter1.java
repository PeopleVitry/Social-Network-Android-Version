package com.formation.projetglobal;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Listedamisdapter1 extends BaseExpandableListAdapter {

	protected static final int MODE_WORLD_READABLE = 0;
	public static Context context;
	private ArrayList<Groupe> groupes;
	private LayoutInflater inflater;
	private Bitmap mOnlineIcon;
	private Bitmap mOfflineIcon;
	private ImageView icon;
	
	public Listedamisdapter1(Context context, ArrayList<Groupe> groupes) {
		this.context = context;
		this.groupes = groupes;
		inflater = LayoutInflater.from(context);
		mOnlineIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.greenstar);
		mOfflineIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.redstar);
		
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}
	
	public Object getChild(int gPosition, int cPosition) {
		return groupes.get(gPosition).getObjets().get(cPosition);
	}

	public long getChildId(int gPosition, int cPosition) {
		return cPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final Ami objet = (Ami) getChild(groupPosition, childPosition);
		
		
		ChildViewHolder childViewHolder;
		
        if (convertView == null) {
        	childViewHolder = new ChildViewHolder();
        	
            convertView = inflater.inflate(R.layout.group_childlistedamis, null);
            
            childViewHolder.textViewChild = (TextView) convertView.findViewById(R.id.TVChild);
           // childViewHolder.buttonChild = (Button) convertView.findViewById(R.id.button1);
            childViewHolder.icon = (ImageView) convertView.findViewById(R.id.imageView1);
            
            convertView.setTag(childViewHolder);
        } else {
        	childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        
        childViewHolder.textViewChild.setText(objet.getNom());
        childViewHolder.icon.setImageBitmap(objet.getConnecte() == 1 ? mOnlineIcon : mOfflineIcon);
      
       childViewHolder.textViewChild.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				Intent intent = new Intent(context,Profile.class);
				intent.putExtra( "email", objet.getEmail());
				intent.putExtra( "nom", objet.getNom());
				intent.putExtra( "prenom", objet.getPrenom());
				intent.putExtra( "connecte", objet.getConnecte());
				intent.putExtra("guid", objet.getGuid()+"");
				
				/////recuperer nos données qui sont stocké dans le fichier  getSharedPreferences
				SharedPreferences myPrefss = context.getSharedPreferences("myPrefss", MODE_WORLD_READABLE);
		        SharedPreferences.Editor prefsEditor = myPrefss.edit();
		        prefsEditor.putString("guidcontact", ""+objet.getGuid());
		        prefsEditor.putString("emailcontact", ""+objet.getEmail());
		        prefsEditor.commit();
		        /////////////////////////////////////////////////
				context.startActivity(intent);				
			}
		});
        
        return convertView;
	}

	public int getChildrenCount(int gPosition) {
		return groupes.get(gPosition).getObjets().size();
	}

	public Object getGroup(int gPosition) {
		return groupes.get(gPosition);
	}

	public int getGroupCount() {
		return groupes.size();
	}

	public long getGroupId(int gPosition) {
		return gPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder gholder;
		
		Groupe group = (Groupe) getGroup(groupPosition);
		
        if (convertView == null) {
        	gholder = new GroupViewHolder();
        	
        	convertView = inflater.inflate(R.layout.group_rowlistedamis, null);
        	
        	gholder.textViewGroup = (TextView) convertView.findViewById(R.id.TVGroup);
        	
        	convertView.setTag(gholder);
        } else {
        	gholder = (GroupViewHolder) convertView.getTag();
        }
        
        gholder.textViewGroup.setText(group.getNom());
        
        return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
	class GroupViewHolder {
		public TextView textViewGroup;
	}
	
	class ChildViewHolder {
		public ImageView icon;
		public TextView textViewChild;
		public Button buttonChild;
	}

}
