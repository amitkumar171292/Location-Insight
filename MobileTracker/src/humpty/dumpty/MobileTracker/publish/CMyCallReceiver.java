package humpty.dumpty.MobileTracker.publish;

import java.lang.reflect.Method;
import com.android.internal.telephony.ITelephony;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CMyCallReceiver extends BroadcastReceiver {
	private ITelephony telephonyService;
	CDatab db;
	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			// This code will execute when the phone has an incoming call
			
			// get the phone number 
			String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Toast.makeText(context, "Call from:" +incomingNumber, Toast.LENGTH_LONG).show();
			TelephonyManager telephony = (TelephonyManager) 
			context.getSystemService(Context.TELEPHONY_SERVICE);
			db=new CDatab(context);
			Cursor cur=db.readData();
			 try{
		         if (cur.moveToFirst()) {
		        	 int numberColumn=cur.getColumnIndexOrThrow(CSchema.KEY_NUMBER);
		        	 do {
			               String number = cur.getString(numberColumn);
			               if((incomingNumber.equalsIgnoreCase(number))||(incomingNumber.equalsIgnoreCase("+91"+number))||(incomingNumber.equalsIgnoreCase("0"+number)))
							{
								try
								{
									Class<?> c = Class.forName(telephony.getClass().getName());
									   Method m = c.getDeclaredMethod("getITelephony");
									   m.setAccessible(true);
									   telephonyService = (ITelephony) m.invoke(telephony);
									  // mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
									   //telephonyService.silenceRinger();
								 telephonyService.endCall();
							  } catch (Exception e) {
							   e.printStackTrace();
							  }
							  
							}
						 else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)|| intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
										TelephonyManager.EXTRA_STATE_OFFHOOK)) {
							// This code will execute when the call is disconnected
							Toast.makeText(context, "Detected call hangup event", Toast.LENGTH_LONG).show();
							break;
						}
			 
			          } while (cur.moveToNext());
			        }
			 }
			 finally{
			      cur.close();
			    }
		           
			
			 
		}
}}

