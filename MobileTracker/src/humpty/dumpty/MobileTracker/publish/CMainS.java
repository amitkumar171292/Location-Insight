package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CMainS extends Activity{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ccallmainscreen);
	}
	public void onc(View v)
	{
		switch(v.getId())
		{
		case R.id.b1:
		{
			ImageView b=(ImageView)findViewById(R.id.b1);
			//b.setBackgroundColor(880);
			Intent i=new Intent(this,CAddc.class);
			startActivity(i);
		}
			break;
		case R.id.b2:
		{
			ImageView b=(ImageView)findViewById(R.id.b2);
			//b.setBackgroundColor(880);
			Intent i=new Intent(CMainS.this,CDelRecord.class);
			startActivity(i);
		}
		break;
		case R.id.b3:
		{
			ImageView b=(ImageView)findViewById(R.id.b3);
			//b.setBackgroundColor(880);
			Intent i=new Intent(CMainS.this,CDisplay.class);
			startActivity(i);
		}
			break;
	}
	}
	public void onBackPressed()
	{
		Intent intent=new Intent(getApplicationContext(),AUsermainscreen.class);
    	startActivity(intent);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if ((keyCode == KeyEvent.KEYCODE_HOME)) {
	        //do your stuff
	    	int pid = android.os.Process.myPid();
		    android.os.Process.killProcess(pid);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
