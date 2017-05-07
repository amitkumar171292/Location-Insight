package humpty.dumpty.MobileTracker.publish;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MOutgoingCall extends BroadcastReceiver {
	SmsManager sms = SmsManager.getDefault();
	
    public void onReceive(Context context, Intent intent) {
    SharedPreferences  pref =context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);  
    String no1 = pref.getString("no1", null);
    String sim = pref.getString("simno",null);
    String status = pref.getString("status", null);
    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
    String sim_sr=telephony.getSimSerialNumber();
    try
    {
    	if(status.equals("1"))
    	{
    		if((sim.equals(sim_sr)))
    		{
    //Toast.makeText(context, "HI am on2" , Toast.LENGTH_LONG).show();
    			MOutgoingCall bx=new MOutgoingCall();
    			bx.abortBroadcast();
    		}
    		else
    		{
    			try
    			{
    				String number=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
    				Log.d(MOutgoingCall.class.getSimpleName(),intent.toString()+",call to:"+number);
    				//Toast.makeText(context,"OUTGOING CALL"+number,Toast.LENGTH_LONG).show();
    				//String extraState =intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    				//if (extraState.equals(TelephonyManager.EXTRA_STATE_RINGING))
    				//{
    					//String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
    					sms.sendTextMessage(no1, null, "YOUR ANDROID PHONE HAVE A NEW OUTGOING CALL,CALLER NO IS:= "+number, null, null);
    				//}
    			}catch (Exception e) 
    			{
    			}
    		}
    	}
    	else
    	{
    //Toast.makeText(context, "HI am off2" , Toast.LENGTH_LONG).show();
    		MOutgoingCall bx=new MOutgoingCall();
    		bx.abortBroadcast();
    	}	
    }catch (Exception e)
    {
    //Toast.makeText(context, "i am Error incomming call" , Toast.LENGTH_LONG).show();
    }
    }
}
