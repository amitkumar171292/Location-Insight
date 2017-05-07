package humpty.dumpty.MobileTracker.publish;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class SMyService extends Service {
	BAEScreenOnOffReceiver longi,lati;
	private static final String TAG = "MyService";
	private SensorManager mSensorManager;
	  private float mAccel; // acceleration apart from gravity
	  private float mAccelCurrent; // current acceleration including gravity
	  private float mAccelLast; // last acceleration including gravity
	  SensorManager sensorMgr;
	  
		Editor se;
		Sensor sensor;
	
		ImageView power, contacts, credits, howToUse;

		boolean state = false;
		String c,c1,c2;
		String ms;
		 double LONGITUDE;
		 double LATITUDE;
		 String myAddress;
		int x;
		SharedPreferences sharedpreferences;
		
		SmsManager smsManager;
		
		
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, " Service Created", Toast.LENGTH_LONG).show();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	    mAccel = 0.00f;
	    mAccelCurrent = SensorManager.GRAVITY_EARTH;
	    mAccelLast = SensorManager.GRAVITY_EARTH;
	 
		Log.d(TAG, "onCreate");
		
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, " Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		 mSensorManager.unregisterListener(mSensorListener);
		
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		
	}
	private final SensorEventListener mSensorListener = new SensorEventListener() 
	{
		
public void onSensorChanged(SensorEvent event) {
	
	
	
	
	sharedpreferences = getSharedPreferences("CONTACTS", Context.MODE_APPEND);
    se = sharedpreferences.edit();
	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	 smsManager = SmsManager.getDefault();
	smsManager = SmsManager.getDefault();
	 c=sharedpreferences.getString("CONTACT1", "Default");
	 c1=sharedpreferences.getString("CONTACT2", "Default");
	 c2=sharedpreferences.getString("CONTACT3", "Default");
	 ms=sharedpreferences.getString("MESSAGE", "Default");
	float x = event.values[0];
      float y = event.values[1];
      float z = event.values[2];
      mAccelLast = mAccelCurrent;
      mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
      float delta = mAccelCurrent - mAccelLast;
      mAccel = mAccel * 0.9f + delta; // perform low-cut filter
      if (mAccel > 12) {
    	  smsManager.sendTextMessage(c, null, ms,null, null);
			smsManager.sendTextMessage(c1, null, ms,null, null);
		smsManager.sendTextMessage(c2, null, ms,null, null);
			Toast.makeText(getBaseContext(), "Send", Toast.LENGTH_LONG)
					.show();
    	}
	
	
}

public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}};

protected void onResume() {
	  
    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
  }

  protected void onPause() {
    mSensorManager.unregisterListener(mSensorListener);
    
  }
  
}
