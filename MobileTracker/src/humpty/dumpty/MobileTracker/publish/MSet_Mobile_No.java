package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MSet_Mobile_No extends Activity {
	String s1,s2,s3,s4,s5="",s6="",s7="",s8,s9,s10,s11,s12,s13,s14,match;
	EditText editno1,editno2;
    TextView data;
    TextView t1,t2;
    MDbhelpermob mHelper;
    SQLiteDatabase dataBase;
	ContentValues values;
	String pass,pass2,userId,user_fName;
	Cursor mCursor;
	ImageView b2,b1;
	 private ProgressDialog progress;
	//Animation animScale ;
	//Animation animRotate ;
	  //Animation animFadein;
	  TextView vis;
	  Typeface tf;
	  SharedPreferences pref;
    @Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.mset_sms);
    b2 = (ImageView) findViewById(R.id.button2);
    b1 = (ImageView) findViewById(R.id.smsbutton);
	editno1 = (EditText) findViewById(R.id.editno1);
	editno2 = (EditText) findViewById(R.id.editno2);
	//t1=(TextView)findViewById(R.id.phoneNum1);
	//t2=(TextView)findViewById(R.id.phoneNum2);
	//data=(TextView)findViewById(R.id.textView12);
	//vis=(TextView)findViewById(R.id.textView1111);
	//animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
    //animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
    //animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
    //set font
    //tf = Typeface.createFromAsset(getAssets(),"fonts/ALGER.TTF");
    //b1.setTypeface(tf);
    //b2.setTypeface(tf);
    //t1.setTypeface(tf);
    //t2.setTypeface(tf);
    //vis.setTypeface(tf);
    pref = getSharedPreferences("MyFile", 0);
	mHelper = new MDbhelpermob(this);
	dataBase = mHelper.getWritableDatabase();
	values=new ContentValues();
	mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbhelpermob.TABLE_NAME, null);
    if (mCursor.moveToFirst()) {
			do {
				  s1=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_ID));
				   s2=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO1));
				   s3=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO2));
				   s4=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_SIMNO));
				   s5=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_STATUS));       
	               s6="Your Save Mobile No="+s2+"\n"+"Your Sim Serial No="+s4+"\n"+"Mobile Tracking Status="+s5+"\n";
	               s7+=s6;
			       } while (mCursor.moveToNext());
		           }
	 mCursor.close();
	 dataBase.close();
	 editno1.setText(s2);
	 editno2.setText(s3);
	 Toast.makeText(this,"Your Save Details:"+"\n"+s7, Toast.LENGTH_LONG).show();
	 //data.setText("Your Save Details.."+"\n"+s7);
    if(s5.equals(""))
	{
    	b2.setImageResource(R.drawable.off);
	 //b2.setText("Theft Tracker ON");
	 match="";
	}
	else if(s5.equals("0")) {
		b2.setImageResource(R.drawable.off);
	//b2.setText("Theft Tracker ON");
	match="0";
	}
	else if(s5.equals("1"))
	{
		b2.setImageResource(R.drawable.on);
	 //b2.setText("Theft Tracker OFF");
	 match="1";
		}
		
	}
   public void  smsset(View arg0) 
   {
	mHelper = new MDbhelpermob(this);
	dataBase = mHelper.getWritableDatabase();
	values=new ContentValues();
	TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
   	s8 = editno1.getText().toString();
   	s9 = editno2.getText().toString();
   	if(s8.equals(s9))
   {	
   if(match.equals(""))
   {
   if(s8.length()==10&&s9.length()==10)
   {        
	        //arg0.startAnimation(animScale);
   			s10=tm.getSimSerialNumber();
   		   	String status="0";
   			values=new ContentValues();
   		    values.put(MDbhelpermob.KEY_NO1,s8);	
   		   	values.put(MDbhelpermob.KEY_NO2,s9);
   		   	values.put(MDbhelpermob.KEY_SIMNO,s10);
   		   	values.put(MDbhelpermob.KEY_STATUS,status);
   		   	dataBase.insert(MDbhelpermob.TABLE_NAME, null, values);
   		   	//dataBase.update(DbHelper.TABLE_NAME, values,DbHelper.KEY_ID +"="+id, null);
   		   	dataBase.close();
   		   //	////Toast.makeText(this, "Set Your Mobile No..", ////Toast.LENGTH_LONG).show();
   		   	
   		 Toast.makeText(this,"Set Your Mobile!!", Toast.LENGTH_LONG).show();
   		    //vis.setText("Set Your Moblie No");
   	   	    //vis.setVisibility(View.VISIBLE);
   	     
   	     // start the animation
   	       //vis.startAnimation(animFadein);
   		   //////Toast.makeText(this, "insert =", ////Toast.LENGTH_LONG).show();
   		   finish();
   		   Intent inserts=new Intent(getApplicationContext(),MSet_Mobile_No.class);
		    startActivity(inserts);
   	}
   	else
       {
	     Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
	     findViewById(R.id.editno1).startAnimation(shake);
	     Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
	     findViewById(R.id.editno2).startAnimation(shake1);
	    // ////Toast.makeText(this, "Enter  Mobile ", ////Toast.LENGTH_LONG).show();
	     Toast.makeText(this,"Enter 10 Digit Mobile Number", Toast.LENGTH_LONG).show();
	     //vis.setText("Enter 10 Digite Mobile No");
         //vis.setVisibility(View.VISIBLE);
         
         // start the animation
         //vis.startAnimation(animFadein);
   }
   }
   else
   {
	   if(s8.length()==10&&s9.length()==10)
	   {
	        //arg0.startAnimation(animScale);
			s10=tm.getSimSerialNumber();
		   	String status="0";
		   	String id="1";
			values=new ContentValues();
		    values.put(MDbhelpermob.KEY_NO1,s8);	
		   	values.put(MDbhelpermob.KEY_NO2,s9);
		   	values.put(MDbhelpermob.KEY_SIMNO,s10);
		   	values.put(MDbhelpermob.KEY_STATUS,status);
		   	//dataBase.insert(Dbhelpermob.TABLE_NAME, null, values);
		   	dataBase.update(MDbhelpermob.TABLE_NAME, values,MDbhelpermob.KEY_ID +"="+id, null);
		   	dataBase.close();
		   //	////Toast.makeText(this, "Set Your Mobile No..", ////Toast.LENGTH_LONG).show();
		 //vis.setText("Set Your Moblie No");
		 Toast.makeText(this,"Set Your Mobile Number", Toast.LENGTH_LONG).show();
	   	 //vis.setVisibility(View.VISIBLE);
	     
	     // start the animation
	     //vis.startAnimation(animFadein);
	 	 ////Toast.makeText(this, "update", ////Toast.LENGTH_LONG).show();
	 	 finish();
   		 Intent inserts=new Intent(getApplicationContext(),MSet_Mobile_No.class);
		 startActivity(inserts);
		 	editno1.setText(s8.toString());
		   	editno2.setText(s9.toString());
	   }
	   else
	   {
		   Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		     findViewById(R.id.editno1).startAnimation(shake);
		     Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
		     findViewById(R.id.editno2).startAnimation(shake1);
		    // ////Toast.makeText(this, "Enter  Mobile ", ////Toast.LENGTH_LONG).show();
		     Toast.makeText(this,"Enter 10 Digit Mobile Number", Toast.LENGTH_LONG).show();
		     //vis.setText("Enter 10 Digite Mobile No");
	         //vis.setVisibility(View.VISIBLE);
	         
	         // start the animation
	         //vis.startAnimation(animFadein);
	   }
	   
   }

   }
   	else
   	{
   	 Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
     findViewById(R.id.editno1).startAnimation(shake);
     Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
     findViewById(R.id.editno2).startAnimation(shake1);
    // ////Toast.makeText(this, "Enter  Mobile ", ////Toast.LENGTH_LONG).show();
     Toast.makeText(this,"Both Mobile Are Not Same", Toast.LENGTH_LONG).show();
     //vis.setText("Both Moblie NO Not Same..");
     //vis.setVisibility(View.VISIBLE);
     
     // start the animation
     //vis.startAnimation(animFadein);
   	}
  
}

  

public void tracker( View arg0) 
{
	
	if(match.equals(""))
	{
		Toast.makeText(this,"Set The Mobile Number First", Toast.LENGTH_LONG).show();
		//arg0.startAnimation(animFadein);
		//b2.setImageResource(R.drawable.on);
		//b2.setText("Thift Tracker OFF");
		//Toast.makeText(this,"Your Save Details:"+"\n"+s7, Toast.LENGTH_LONG).show();
        //vis.setVisibility(View.VISIBLE);
        // start the animation
        //vis.startAnimation(animFadein);
		
	}
	else if(match.equals("0")) 
	{
		mHelper=new MDbhelpermob(this);
		//arg0.startAnimation(animFadein);
	    dataBase = mHelper.getWritableDatabase();
		mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbhelpermob.TABLE_NAME, null);
		try
		{
			if (mCursor.moveToFirst()) 
			{
				do {
						s1=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_ID));
						s2=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO1));
						s3=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO2));
						s4=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_SIMNO));
						s5=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_STATUS));
						s6="id="+s1+"\n"+"no1="+s2+"\n"+"no2="+s3+"\n"+"simno="+s4+"\n"+"status="+s5+"\n";
			            s7+=s6;
			       } while (mCursor.moveToNext());
			}
			mCursor.close();
			dataBase.close();
		}
		catch (Exception e)
	    {
	    }
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		s4=tm.getSimSerialNumber();
   	    String status="1";
   	    mHelper=new MDbhelpermob(this);
		dataBase=mHelper.getWritableDatabase();
		values=new ContentValues();
   		values.put(MDbhelpermob.KEY_NO1,s2);	
   		values.put(MDbhelpermob.KEY_NO2,s3);
   		values.put(MDbhelpermob.KEY_SIMNO,s4);
   		values.put(MDbhelpermob.KEY_STATUS,status);
   		//dataBase.insert(Dbhelpermob.TABLE_NAME, null, values);
   		dataBase.update(MDbhelpermob.TABLE_NAME, values,MDbhelpermob.KEY_ID +"="+s1, null);
   		dataBase.close();
   		//Intent inserts=new Intent(getApplicationContext(),Startbroadcost.class);
   		//startActivity(inserts);
           final SharedPreferences.Editor   editor = pref.edit();
   		    editor.putString("no1", s2);
		    editor.commit();
		    editor.putString("simno", s4);
		    editor.commit();
		    editor.putString("status", status);
		    editor.commit();		
		    //call for sim chage
	       Intent intent = new Intent(MSet_Mobile_No.this, MMainBroadCast.class);
		   sendBroadcast(intent);
		    //call for sms get
		   Intent intent1 = new Intent(MSet_Mobile_No.this, MIncomingCall.class);
		   sendBroadcast(intent1);
		    //call for phone get
		   Intent intent2 = new Intent(MSet_Mobile_No.this, MIncomingSms.class);
		   sendBroadcast(intent2);
		   Intent intent3 = new Intent(MSet_Mobile_No.this,MOutgoingCall.class);
		   sendBroadcast(intent3);
		   // ////Toast.makeText(getApplicationContext(),"your data base info no1"+s2+"your no2="+s3+"your statues="+s5,////Toast.LENGTH_LONG).show();
		  // ////Toast.makeText(getApplicationContext(),fix_id+ " saved",////Toast.LENGTH_LONG).show();
		   finish();
		  // final Thread ct = Thread.currentThread();
   	      	//vis.setText("Theft Tracker On");
   	     //vis.setVisibility(View.VISIBLE);
		   Intent inserts=new Intent(getApplicationContext(),MSet_Mobile_No.class);
  	      	startActivity(inserts);
   	     
     // start the animation
        //vis.startAnimation(animFadein);
   	  b2.setImageResource(R.drawable.off);
   	     //b2.setText("Theft Tracker ON");
 
			Toast.makeText(this,"Theft Tracker On", Toast.LENGTH_LONG).show();
   	
   	   }
	
		else 
			//if(match.equals("1"))
		{
		   //arg0.startAnimation(animFadein);
			b2.setImageResource(R.drawable.on);
			  // b2.setText("Theft Tracker OFF");
			   mHelper=new MDbhelpermob(this);
			   dataBase = mHelper.getWritableDatabase();
			   mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbhelpermob.TABLE_NAME, null);
			   try
			   {
				   if (mCursor.moveToFirst())
				   {
					   do 
					   {
					    	 s1=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_ID));
					    	 s2=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO1));
					    	 s3=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_NO2));
					    	 s4=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_SIMNO));
					    	 s5=mCursor.getString(mCursor.getColumnIndex(MDbhelpermob.KEY_STATUS));	   
					    	 s6="id="+s1+"\n"+"no1="+s2+"\n"+"no2="+s3+"\n"+"simno="+s4+"\n"+"status="+s5+"\n";
					    	 s7+=s6;
				   		}while (mCursor.moveToNext());	
					   	mCursor.close();
					   	dataBase.close();
	   	    			}
	                }
	    			catch (Exception e)
	    			{
	    			}
	    			TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		    		s4=tm.getSimSerialNumber();
		            String status="0";
		            mHelper=new MDbhelpermob(this);
		    	 	dataBase=mHelper.getWritableDatabase();
		    	    values=new ContentValues();
		        	//values=new ContentValues();
		            values.put(MDbhelpermob.KEY_NO1,s2);	
		        	values.put(MDbhelpermob.KEY_NO2,s3);
		        	values.put(MDbhelpermob.KEY_SIMNO,s4);
		        	values.put(MDbhelpermob.KEY_STATUS,status);
		        	dataBase.update(MDbhelpermob.TABLE_NAME, values,MDbhelpermob.KEY_ID +"="+s1, null);
		        	dataBase.close();
	          
	            // start the animation
	            //vis.startAnimation(animFadein);
		            final SharedPreferences.Editor   editor = pref.edit();
		   		    editor.putString("no1", s2);
				    editor.commit();
				    editor.putString("simno", s4);
				    editor.commit();
				    editor.putString("status", status);
				    editor.commit();
			    //call for sim chage
			       Intent intent = new Intent(MSet_Mobile_No.this, MMainBroadCast.class);
				   sendBroadcast(intent);
				    //call for sms get
				   Intent intent1 = new Intent(MSet_Mobile_No.this, MIncomingCall.class);
				   sendBroadcast(intent1);
				    //call for phone get
				   Intent intent2 = new Intent(MSet_Mobile_No.this, MIncomingSms.class);
				   sendBroadcast(intent2);
				   Intent intent3 = new Intent(MSet_Mobile_No.this,MOutgoingCall.class);
				   sendBroadcast(intent3);
				   // ////Toast.makeText(getApplicationContext(),"your data base info no1"+s2+"your no2="+s3+"your statues="+s5,////Toast.LENGTH_LONG).show();
				  // ////Toast.makeText(getApplicationContext(),fix_id+ " saved",////Toast.LENGTH_LONG).show();
				   finish();
	           
	            
				   Intent inserts=new Intent(getApplicationContext(),MSet_Mobile_No.class);
				   startActivity(inserts);
				   Toast.makeText(this,"Theft Tracker Off", Toast.LENGTH_LONG).show();
				   //vis.setText("Theft Tracker OFF");
		           //vis.setVisibility(View.VISIBLE);
		           //b2.setText("Theft Tracker OFF");
		           b2.setImageResource(R.drawable.on);
	        			
	            }
	   	}
	    	
}
	        
	      
	        