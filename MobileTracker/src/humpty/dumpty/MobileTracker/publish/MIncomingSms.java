package humpty.dumpty.MobileTracker.publish;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;


public class MIncomingSms extends BroadcastReceiver {
    // Get the object of SmsManager
    SmsManager sms = SmsManager.getDefault();
    public void onReceive(Context context, Intent intent) {
    // Retrieves a map of extended data from the intent.
	Bundle bundle = intent.getExtras();
	SharedPreferences  pref =context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);  
    String no1 = pref.getString("no1", null);
    String sim = pref.getString("simno", null);
    String status = pref.getString("status", null);
    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
    String sim_sr=telephony.getSimSerialNumber();
    try
    {
    if(status.equals("1"))
    {
    if((sim.equals(sim_sr)))
    {
    //Toast.makeText(context, "HI am on3" , Toast.LENGTH_LONG).show();
    MIncomingSms bx=new MIncomingSms();
    bx.abortBroadcast();
    }
    else
    {
    try {
  	if (bundle != null)
  	{
  	final Object[] pdusObj = (Object[]) bundle.get("pdus");
  	for (int i = 0; i < pdusObj.length; i++)
  	{
  	SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
  	String phoneNumber = currentMessage.getDisplayOriginatingAddress();
  	String senderNum = phoneNumber;
  	String message = currentMessage.getDisplayMessageBody();
    sms.sendTextMessage(no1,null, "Hi.......YOUR ANDROID PHONE HAVE A NEW SMS"+"\n"+"SMS IS="+"\n"+message+"\nSMS SENDER NO IS="+senderNum, null, null);
  	}
  	}
    } 
    catch (Exception e)
    {
	//Log.e("SmsReceiver", "Exception smsReceiver" +e);
    } 		       
    }
    }
    else
    {
    //Toast.makeText(context, "HI am off3" , Toast.LENGTH_LONG).show();
    MIncomingSms bx=new MIncomingSms();
    bx.abortBroadcast();
    }	
    }
    catch (Exception e) {
    //Toast.makeText(context, "I am error for incomming sms" , Toast.LENGTH_LONG).show();
    }
    }
}