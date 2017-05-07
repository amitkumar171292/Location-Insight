package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MTrackermainscreen extends Activity
{
	public MDbHelper mHelper;
	public MDbhelpermob mHelper1;
	public SQLiteDatabase dataBase;
	public SQLiteDatabase dataBase1;
	 final String id="1";
	String scpassword;
	String scmob;
	String scsms;
	String scsim;
	Editor se;
	String scstatus;
	Animation animTranslate; 
    Animation animAlpha;
    Animation animScale ;
    Animation animRotate ;
    ImageView b1,b2,b3,b4,b5;
    Typeface tf;
    public void onBackPressed() 
	{
		finish();
		//overridePendingTransition(R.anim.translation_right_to_left, 0);
		super.onBackPressed();
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mtrackermainscreen);
         animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
         animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
         animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
         animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
         b1=(ImageView)findViewById(R.id.setmobno);
         b2=(ImageView)findViewById(R.id.siminfo);
         //b3=(ImageView)findViewById(R.id.changepass);
         b4=(ImageView)findViewById(R.id.resetapps);
         b5=(ImageView)findViewById(R.id.contact);
       //set font
	        tf = Typeface.createFromAsset(getAssets(),"fonts/ALGER.TTF");
	       // b1.setTypeface(tf);
	       // b2.setTypeface(tf);
		   // b3.setTypeface(tf);
		   // b4.setTypeface(tf);
		   // b5.setTypeface(tf);
		    mHelper = new MDbHelper(this);
		    mHelper1 = new MDbhelpermob(this);
	}
	public void sim(View arg0) {
		arg0.startAnimation(animAlpha);
		Intent inserts1=new Intent(getApplicationContext(),MShow_user_mobile_details.class);
			startActivity(inserts1);	
			
	}
	public void setsms(View arg0) {
		arg0.startAnimation(animTranslate);
		Intent inserts2=new Intent(getApplicationContext(),MSet_Mobile_No.class);
			startActivity(inserts2);
			
	}
	
	public void contact(View arg0)
	{
		arg0.startAnimation(animScale);
		Intent inserts4=new Intent(getApplicationContext(),ACredits.class);
		startActivity(inserts4);
	}

	public void reset(View arg0) 
	{
			scpassword=null;
			scmob=null;
			scsms=null;
			scsim=null;
			scstatus=null;
			dataBase=mHelper.getWritableDatabase();
			dataBase1=mHelper1.getWritableDatabase();
			ContentValues values=new ContentValues();
			values.put(MDbHelper.KEY_FNAME,scpassword);
			values.put(MDbhelpermob.KEY_NO1,scmob);
			values.put(MDbhelpermob.KEY_NO2,scsms);
			values.put(MDbhelpermob.KEY_SIMNO,scsim);
			values.put(MDbhelpermob.KEY_STATUS,scstatus);
			//insert data into database
		  	dataBase.delete(MDbHelper.TABLE_NAME, null, null);
		  	dataBase1.delete(MDbhelpermob.TABLE_NAME, null, null);
		   //dataBase.update(DbHelper.TABLE_NAME, values, DbHelper.KEY_ID+"="+id, null);
		  	scpassword+=id;
		  	scmob+=id;
		  	scsms+=id;
		  	scsim+=id;
		  	scstatus+=id;
		  	dataBase.close();
		  	dataBase1.close();
			Toast.makeText(this, "App Re-set Done..", Toast.LENGTH_LONG).show();
			arg0.startAnimation(animRotate);
			Intent inserts22=new Intent(getApplicationContext(),ADisplayActivity.class);
			startActivity(inserts22);	
		//close database
	}

}
