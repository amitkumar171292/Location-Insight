package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AGetpassword extends Activity {
	EditText pass;
	TextView g1,g2;
    ImageView g4,g5;
	
    MDbHelper mHelper;
    SQLiteDatabase dataBase;
	
    String spassword,databasepassword,userId;
	
    Animation animTranslate;
    Animation animFadein;
    
    Typeface tf;
	
    //Main Method
    @Override
    public void onCreate(Bundle savedInstanceState) {	    
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.agetpassword);
        
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        
        pass=(EditText)findViewById(R.id.g3);
        //g1=(TextView)findViewById(R.id.g1);
        //g2=(TextView)findViewById(R.id.g2);
        g4=(ImageView)findViewById(R.id.g4);
        g5=(ImageView)findViewById(R.id.check1);
        
        //set font
        //tf = Typeface.createFromAsset(getAssets(),"fonts/ALGER.TTF");
        //pass.setTypeface(tf);
        //g1.setTypeface(tf);
	    //g2.setTypeface(tf);
	    //g4.setTypeface(tf);
	    
	    //set error animations
	    animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
	    mHelper = new MDbHelper(this);
		
	    //show record 
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst())
        {
		do
		{
		userId=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_ID));
		databasepassword=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_FNAME));	              
		}
		while (mCursor.moveToNext());
		}
		mCursor.close();
		dataBase.close();
        }        
         
        //match password on click method
	     public void  getpass( View arg0) 
	     {
		 spassword=pass.getText().toString().trim();
		 
		 if(databasepassword.equals(spassword))
		 {
		 arg0.startAnimation(animTranslate);
	     Intent inserts=new Intent(getApplicationContext(),AUsermainscreen.class);
		 startActivity(inserts);
		 finish();
		 }
		 
		 else
		 {
		 Animation shake1 = AnimationUtils.loadAnimation(this, R.anim.shake1);
		 findViewById(R.id.g3).startAnimation(shake1);
		 Toast.makeText(this,"Wrong Password Entered", Toast.LENGTH_LONG).show();
		 //set the error animation
		 //g1.setVisibility(View.VISIBLE);
	   	 // start the animation
	   	 //g1.startAnimation(animFadein);
		 }
		}
	     public void check(View arg0)
	     {
	    	 //arg0.startAnimation(animTranslate);
	    	 Intent i=new Intent(getApplicationContext(),AForgetpass.class);
			 startActivity(i);
	     }
	     
   }
