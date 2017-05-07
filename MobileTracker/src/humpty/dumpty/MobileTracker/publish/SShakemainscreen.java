package humpty.dumpty.MobileTracker.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SShakemainscreen extends Activity  {
	SensorManager sensorMgr;
	SharedPreferences sharedpreferences;
	Sensor sensor;
	ImageView power, contacts, credits, howToUse;
	boolean state = false;
	Editor se;
	String s,match;
	int x;
	Intent trace;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sshakemainscreen);
		//credits = (ImageView) findViewById(R.id.imageCredits);
		power = (ImageView) findViewById(R.id.imagePower);
		contacts = (ImageView) findViewById(R.id.imageContacts);
		//howToUse = (ImageView) findViewById(R.id.imageHowToUse);
		power.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (state == false) {
					power.setImageResource(R.drawable.on);
					Intent trace = new Intent(getBaseContext(),SMyService.class);
					startService(trace);
					
				
					state = true;
				} else  {
					power.setImageResource(R.drawable.off);
					
					
					
						Intent trace = new Intent(getBaseContext(),SMyService.class);
						stopService(trace);
					
					state = false;
				}
				
				
			}
		});

	}
	public void switchToContacts(View v) 
	{
		Intent i = new Intent(getBaseContext(), SManageContacts.class);
		startActivity(i);
		//overridePendingTransition(R.anim.translation_left_to_right, 0);
	}

}