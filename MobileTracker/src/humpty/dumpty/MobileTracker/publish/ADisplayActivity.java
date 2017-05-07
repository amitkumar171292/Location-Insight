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
import android.widget.ImageView;

public class ADisplayActivity extends Activity {
    public MDbHelper mHelper;
	public SQLiteDatabase dataBase;
    String userId,user_fName;
    String pass,pass2;
	ImageView start;
	Animation animTranslate,animRotate;
	Cursor mCursor;
	@Override
	public void onBackPressed() 
	{
		//overridePendingTransition(R.anim.translation_right_to_left, 0);
		//super.onBackPressed();
		//finish();
		 Intent intent = new Intent(Intent.ACTION_MAIN);
		 intent.addCategory(Intent.CATEGORY_HOME);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 startActivity(intent);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	setContentView(R.layout.adisplay_activity);
	
	//Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ALGER.TTF");
	start=(ImageView)findViewById(R.id.button112);
	//start.setTypeface(tf);
    
	animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
	animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
	mHelper = new MDbHelper(this); 
	dataBase = mHelper.getWritableDatabase();
    mCursor = dataBase.rawQuery("SELECT * FROM "+ MDbHelper.TABLE_NAME, null);
    if (mCursor.moveToFirst())
    {
	do
	{
	userId=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_ID));
	user_fName=mCursor.getString(mCursor.getColumnIndex(MDbHelper.KEY_FNAME));
    pass+=user_fName;
    pass2+=userId;
    break;
    } 
	while (mCursor.moveToNext());
    }
	mCursor.close();
	dataBase.close();
	}

   public void sur(View arg0 ) 
   {
   if(pass2==null&&pass==null)
   {
   arg0.startAnimation(animTranslate);
   Intent i = new Intent(getApplicationContext(),ASetpassword.class);
   startActivity(i);
   finish();
   }
   else
   {
    arg0.startAnimation(animTranslate);
	Intent ii = new Intent(getApplicationContext(),AGetpassword.class);
	startActivity(ii);
	finish();
	}
	}
   
   public void help(View arg0) 
   {
	   arg0.startAnimation(animRotate);
		Intent inserts3=new Intent(getApplicationContext(),AHelp.class);
			startActivity(inserts3);
			
	}
}