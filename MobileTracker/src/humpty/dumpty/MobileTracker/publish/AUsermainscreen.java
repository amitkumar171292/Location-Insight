package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class AUsermainscreen extends Activity {
	
	SharedPreferences sharedpreferences;
	String c,c1,c2;
	String ms;
	Animation animTranslate; 
    Animation animAlpha;
    Animation animScale ;
    Animation animRotate;
    public void onBackPressed() 
	{
		//overridePendingTransition(R.anim.translation_right_to_left, 0);
    	/*Intent intent = new Intent(Intent.ACTION_MAIN);
		 intent.addCategory(Intent.CATEGORY_HOME);
		 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 startActivity(intent);*/
    	Intent intent=new Intent(getApplicationContext(),ADisplayActivity.class);
    	startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ausermainscreen);
		animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	public void track(View arg0) 
	{
		arg0.startAnimation(animAlpha);
		Intent inserts1=new Intent(getApplicationContext(),MTrackermainscreen.class);
			startActivity(inserts1);	
			
	}
	public void shake(View arg0) 
	{
		arg0.startAnimation(animTranslate);
		Intent inserts2=new Intent(getApplicationContext(),SShakemainscreen.class);
			startActivity(inserts2);
			
	}
	public void contact(View arg0) 
	{
		arg0.startAnimation(animScale);
		Intent inserts3=new Intent(getApplicationContext(),ACredits.class);
			startActivity(inserts3);
			
	}
	public void changepasssword(View arg0) 
	{
		arg0.startAnimation(animScale);
		Intent inserts3=new Intent(getApplicationContext(),AUpdatepassword.class);
			startActivity(inserts3);		
	}
	public void call(View arg0) 
	{
		arg0.startAnimation(animRotate);
		Intent inserts4=new Intent(getApplicationContext(),CMainS.class);
		startActivity(inserts4);		
	}
	public void power(View arg0) 
	{
		arg0.startAnimation(animAlpha);
		Intent inserts4=new Intent(getApplicationContext(),BScreenOnOff.class);
		startActivity(inserts4);		
	}
}
